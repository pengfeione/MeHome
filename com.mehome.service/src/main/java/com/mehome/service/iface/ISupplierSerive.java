package com.mehome.service.iface;

import java.util.List;

import com.mehome.requestDTO.SupplierBean;

public interface ISupplierSerive {
	public List<SupplierBean> getListByCondition(SupplierBean bean);

	public String addSupplier(SupplierBean bean);

	public Long getSizeByCondition(SupplierBean bean);

	public String updateSupplier(SupplierBean bean);
}
