package com.mehome.service.impl;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public List<HouseBean> getListByCondition(HouseBean bean) {
		List<HouseResource> houseList=houseResourceDAO.getListByCondition(bean);
		List<HouseBean> houseBeanList=new ArrayList<HouseBean>();
		if(houseList!=null&&houseList.size()>0){
			for (HouseResource houseResource : houseList) {
				HouseBean newBean=new HouseBean(houseResource);
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
		Long size=houseResourceDAO.getSizeByCondition(bean);
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

}
