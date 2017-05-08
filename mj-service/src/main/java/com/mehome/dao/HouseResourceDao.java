package com.mehome.dao;

import com.mehome.domain.HouseResource;

public interface HouseResourceDao {
    int delete(Integer houseId);

    int insert(HouseResource record);

    int insertRequired(HouseResource record);

    HouseResource selectById(Integer houseId);

    int updateRequired(HouseResource record);

    int update(HouseResource record);
}