package com.mehome.service.impl;

import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mehome.controller.HouseSupportController;
import com.mehome.dao.BasicFacilitiesDao;
import com.mehome.domain.BasicFacilities;
import com.mehome.utils.AssertUtils;
import com.mehome.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mehome.dao.HouseResourceDao;
import com.mehome.domain.HouseResource;
import com.mehome.requestDTO.HouseBean;
import com.mehome.service.iface.IHouseService;

import static com.sun.tools.attach.VirtualMachine.list;

@Service("IHouseService")
public class HouseServiceImpl implements IHouseService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private HouseResourceDao houseResourceDAO;
    @Autowired
    private BasicFacilitiesDao basicFacilitiesDao;

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
    public String updateHouse(HouseBean bean) {
        HouseResource resource = null;
        try {
            resource = bean.beanToPojo(Boolean.FALSE);
            int row = houseResourceDAO.updateRequired(resource);
        } catch (Exception e) {
            log.error("更新房源出错:" + e);
            return Boolean.FALSE.toString();
        }
        return Boolean.TRUE.toString();
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
