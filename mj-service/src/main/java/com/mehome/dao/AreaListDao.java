package com.mehome.dao;

import com.mehome.domain.AreaList;

public interface AreaListDao {
    int delete(Integer areaId);

    int insert(AreaList record);

    int insertRequired(AreaList record);

    AreaList selectById(Integer areaId);

    int updateRequired(AreaList record);

    int update(AreaList record);
}