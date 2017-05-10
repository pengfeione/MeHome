package com.mehome.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mehome.requestDTO.HouseBean;
import com.mehome.service.iface.IHouseService;
@Service("IHouseService")
public class HouseServiceImpl implements IHouseService {

	@Override
	public List<HouseBean> getListByCondition(HouseBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addHouse(HouseBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getSizeByCondition(HouseBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateHouse(HouseBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

}
