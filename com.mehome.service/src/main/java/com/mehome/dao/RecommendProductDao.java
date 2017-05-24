package com.mehome.dao;

import java.util.List;

import com.mehome.domain.RecommendProduct;
import com.mehome.requestDTO.RecommendProductBean;

public interface RecommendProductDao {
    int delete(Integer recommendId);

    int insert(RecommendProduct record);

    int insertRequired(RecommendProduct record);

    RecommendProduct selectById(Integer recommendId);

    int updateRequired(RecommendProduct record);

    int update(RecommendProduct record);
    
    public List<RecommendProduct> getListByCondition(RecommendProductBean bean);
    
    Long getSizeByCondition(RecommendProductBean bean);
    
    Integer getMaxSort();
}