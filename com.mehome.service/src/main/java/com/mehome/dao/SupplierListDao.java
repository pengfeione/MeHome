package com.mehome.dao;

import java.util.List;

import com.mehome.domain.SupplierList;
import com.mehome.requestDTO.SupplierBean;
import com.mehome.requestDTO.SupplierRequestDTO;

public interface SupplierListDao {
    int delete(Integer supplierId);

    int insert(SupplierList record);

    int insertRequired(SupplierList record);

    SupplierList selectById(Integer supplierId);

    List<SupplierList> selectByCondition(SupplierRequestDTO supplier);

    Long countByCondition(SupplierRequestDTO supplier);

    int updateRequired(SupplierList record);

    int update(SupplierList record);

    public List<SupplierList> getListByCondition(SupplierBean bean);

    Long getSizeByCondition(SupplierBean bean);
}