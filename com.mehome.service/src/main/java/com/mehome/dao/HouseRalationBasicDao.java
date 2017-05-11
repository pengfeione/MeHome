package com.mehome.dao;

import java.util.List;

import com.mehome.domain.HouseRalationBasic;
import com.mehome.requestDTO.HouseRBasicBean;

public interface HouseRalationBasicDao {
    int delete(String ralationId);

    int insert(HouseRalationBasic record);

    int insertRequired(HouseRalationBasic record);

    HouseRalationBasic selectById(String ralationId);

    int updateRequired(HouseRalationBasic record);

    int update(HouseRalationBasic record);
    
    public List<HouseRalationBasic> getListByCondition(HouseRBasicBean bean);
    
    Long getSizeByCondition(HouseRBasicBean bean);
}