package com.mehome.service.impl;

import java.util.*;

import com.mehome.dao.BasicFacilitiesDao;
import com.mehome.dao.ProductListDao;
import com.mehome.domain.BasicFacilities;
import com.mehome.domain.ProductList;
import com.mehome.utils.AssertUtils;
import com.mehome.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mehome.dao.HouseResourceDao;
import com.mehome.domain.HouseResource;
import com.mehome.requestDTO.HouseBean;
import com.mehome.service.iface.IHouseService;

@Service("IHouseService")
public class HouseServiceImpl implements IHouseService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private HouseResourceDao houseResourceDAO;
    @Autowired
    private BasicFacilitiesDao basicFacilitiesDao;
    @Autowired
    private ProductListDao productListDao;

    @Override
    public List<HouseBean> getListByCondition(HouseBean bean) {
        List<HouseResource> houseList = houseResourceDAO.getListByCondition(bean);
        List<BasicFacilities> basicFacilities = basicFacilitiesDao.list();
        Map<String, BasicFacilities> map = new HashMap<String, BasicFacilities>();
        for (BasicFacilities base : basicFacilities) {
            map.put(base.getBasicId().toString(), base);
        }
        List<HouseBean> houseBeanList = new ArrayList<HouseBean>();

        if (houseList != null && houseList.size() > 0) {
            for (HouseResource houseResource : houseList) {
                String[] arg = houseResource.getBasicIds().split(",");
                if (arg.length > 0) {
                    List<BasicFacilities> list = new ArrayList<BasicFacilities>();
                    for (String id : arg) {
                        BasicFacilities item = map.get(id);
                        if (null != item) {
                            list.add(item);
                        }
                    }
                    houseResource.setBasicList(list);
                }
                HouseBean newBean = new HouseBean(houseResource);
                houseBeanList.add(newBean);
            }
        }
        return houseBeanList;
    }

    @Override
    public String addHouse(HouseBean bean) {
        HouseResource resource = null;
        try {
            resource = bean.beanToPojo(Boolean.TRUE);
            houseResourceDAO.insert(resource);
        } catch (Exception e) {
            log.error("加入房源出错:" + e);
        }
        return resource.getHouseId() == null ? "" : resource.getHouseId().toString();
    }

    @Override
    public Long getSizeByCondition(HouseBean bean) {
        Long size = houseResourceDAO.getSizeByCondition(bean);
        return size;
    }

    @Override
    public String saveHouse(HouseResource bean) {
        AssertUtils.isNotNull(bean.getProductId(), "产品标识未知！");
        ProductList productList = productListDao.selectById(bean.getProductId());
        AssertUtils.isNotNull(productList, "选择的产品非法！");
        bean.setSupplierId(productList.getSupplierId());
        houseResourceDAO.insertRequired(bean);
        return bean.getHouseId() + "";
    }

    @Override
    public String updateHouse(HouseResource bean) {
        AssertUtils.isNotNull(bean.getHouseId(), "更新标识不能为空！");
        houseResourceDAO.updateRequired(bean);
        return bean.getHouseId() + "";
    }

    @Override
    public HouseResource selectById(Integer houseId) {
        HouseResource house = houseResourceDAO.selectById(houseId);
        if (null == house) {
            return null;
        } else {
            if (StringUtils.isNotNull(house.getDetailpic())) {
                try {
                    String detailPic = house.getDetailpic();
                    List<String> list = new ArrayList<String>();
                    Collections.addAll(list, detailPic.substring(1, detailPic.length() - 1).split(","));
                    house.setDetailPicList(list);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                house.setDetailPicList(new ArrayList<String>());
            }
            if (StringUtils.isNotNull(house.getBasicIds())) {

                String[] arg = house.getBasicIds().split(",");
                if (arg.length > 0) {
                    List<BasicFacilities> basicFacilities = basicFacilitiesDao.list();
                    Map<String, BasicFacilities> map = new HashMap<String, BasicFacilities>();
                    for (BasicFacilities base : basicFacilities) {
                        map.put(base.getBasicId().toString(), base);
                    }
                    List<BasicFacilities> list = new ArrayList<BasicFacilities>();
                    for (String id : arg) {
                        BasicFacilities item = map.get(id);
                        if (null != item) {
                            list.add(item);
                        }
                    }
                    house.setBasicList(list);
                }
            }
        }
        return house;
    }
}
