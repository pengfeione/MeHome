package com.mehome.dao;

import java.util.List;

import com.mehome.domain.ProductRalationWelfare;

public interface ProductRalationWelfareDao {
    int delete(String ralationId);

    int insert(ProductRalationWelfare record);

    int insertRequired(ProductRalationWelfare record);

    ProductRalationWelfare selectById(String ralationId);

    int updateRequired(ProductRalationWelfare record);

    int update(ProductRalationWelfare record);
    
    List<ProductRalationWelfare> getRalationList(Integer productId);
}