package com.mehome.dao;

import com.mehome.domain.RecommendProduct;

public interface RecommendProductDao {
    int delete(Integer recommendId);

    int insert(RecommendProduct record);

    int insertRequired(RecommendProduct record);

    RecommendProduct selectById(Integer recommendId);

    int updateRequired(RecommendProduct record);

    int update(RecommendProduct record);
}