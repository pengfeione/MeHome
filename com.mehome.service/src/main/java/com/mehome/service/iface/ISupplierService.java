package com.mehome.service.iface;

import java.util.List;

import com.mehome.domain.SupplierList;
import com.mehome.requestDTO.SupplierBean;
import com.mehome.requestDTO.SupplierRequestDTO;

public interface ISupplierService {
    public List<SupplierBean> getListByCondition(SupplierBean bean);

    int updateRequired(SupplierList record);


    List<SupplierList> selectByCondition(SupplierRequestDTO supplier);

    Long countByCondition(SupplierRequestDTO supplier);


    public SupplierList selectById(Integer supplierListId);

    int insertRequired(SupplierList record);

    public String updateSupplier(SupplierBean bean);


    public String addSupplier(SupplierBean bean);

    public Long getSizeByCondition(SupplierBean bean);


}
