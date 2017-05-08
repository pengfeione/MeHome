package com.mehome.dao;

import com.mehome.domain.ProductRalationBasic;

public interface ProductRalationBasicDao {
    int delete(String ralationId);

    int insert(ProductRalationBasic record);

    int insertRequired(ProductRalationBasic record);

    ProductRalationBasic selectById(String ralationId);

    int updateRequired(ProductRalationBasic record);

    int update(ProductRalationBasic record);
}