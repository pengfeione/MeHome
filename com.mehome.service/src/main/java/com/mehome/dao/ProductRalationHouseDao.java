package com.mehome.dao;

import com.mehome.domain.ProductRalationHouse;

public interface ProductRalationHouseDao {
    int delete(String ralationId);

    int insert(ProductRalationHouse record);

    int insertRequired(ProductRalationHouse record);

    ProductRalationHouse selectById(String ralationId);

    int updateRequired(ProductRalationHouse record);

    int update(ProductRalationHouse record);
}