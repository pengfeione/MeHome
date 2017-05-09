package com.mehome.dao;

import com.mehome.domain.ProductList;

public interface ProductListDao {
    int delete(Integer productId);

    int insert(ProductList record);

    int insertRequired(ProductList record);

    ProductList selectById(Integer productId);

    int updateRequired(ProductList record);

    int update(ProductList record);
}