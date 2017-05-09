package com.mehome.dao;

import com.mehome.domain.BasicFacilities;

public interface BasicFacilitiesDao {
    int delete(Integer basicId);

    int insert(BasicFacilities record);

    int insertRequired(BasicFacilities record);

    BasicFacilities selectById(Integer basicId);

    int updateRequired(BasicFacilities record);

    int update(BasicFacilities record);
}