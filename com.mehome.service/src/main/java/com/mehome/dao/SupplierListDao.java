package com.mehome.dao;

import com.mehome.domain.SupplierList;

public interface SupplierListDao {
    int delete(Integer supplierId);

    int insert(SupplierList record);

    int insertRequired(SupplierList record);

    SupplierList selectById(Integer supplierId);

    int updateRequired(SupplierList record);

    int update(SupplierList record);
}