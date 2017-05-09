package com.mehome.dao;

import com.mehome.domain.CityList;

public interface CityListDao {
    int delete(Integer cityId);

    int insert(CityList record);

    int insertRequired(CityList record);

    CityList selectById(Integer cityId);

    int updateRequired(CityList record);

    int update(CityList record);
}