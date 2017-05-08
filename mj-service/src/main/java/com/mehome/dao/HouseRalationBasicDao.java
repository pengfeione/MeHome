package com.mehome.dao;

import com.mehome.domain.HouseRalationBasic;

public interface HouseRalationBasicDao {
    int delete(String ralationId);

    int insert(HouseRalationBasic record);

    int insertRequired(HouseRalationBasic record);

    HouseRalationBasic selectById(String ralationId);

    int updateRequired(HouseRalationBasic record);

    int update(HouseRalationBasic record);
}