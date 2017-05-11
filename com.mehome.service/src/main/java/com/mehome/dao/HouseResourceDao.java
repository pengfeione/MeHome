package com.mehome.dao;

import java.util.List;

import com.mehome.domain.HouseResource;
import com.mehome.requestDTO.HouseBean;

public interface HouseResourceDao {
    int delete(Integer houseId);

    int insert(HouseResource record);

    int insertRequired(HouseResource record);

    HouseResource selectById(Integer houseId);

    int updateRequired(HouseResource record);

    int update(HouseResource record);
    
    List<HouseResource> getListByCondition(HouseBean bean);
    
    Long getSizeByCondition(HouseBean bean);
}