package com.mehome.service.iface;

import java.util.List;

import com.mehome.domain.HouseResource;
import com.mehome.requestDTO.HouseBean;


public interface IHouseService {
    public List<HouseBean> getListByCondition(HouseBean bean);

    public String addHouse(HouseBean bean);

    public String saveHouse(HouseResource bean);

    public Long getSizeByCondition(HouseBean bean);

    public String updateHouse(HouseResource bean);

    HouseResource selectById(Integer houseId);
}
