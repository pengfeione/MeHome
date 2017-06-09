package com.mehome.dao;

import com.mehome.domain.BasicFacilities;

import java.util.List;

public interface BasicFacilitiesDao {
    int delete(Integer basicId);

    int insert(BasicFacilities record);

    int insertRequired(BasicFacilities record);

    BasicFacilities selectById(Integer basicId);

    List<BasicFacilities> list();

    int updateRequired(BasicFacilities record);

    int update(BasicFacilities record);
}