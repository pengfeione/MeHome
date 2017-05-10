package com.mehome.service.iface;

import java.util.List;

import com.mehome.requestDTO.ProductBean;

public interface IProductService {
	public List<ProductBean> getListByCondition(ProductBean bean);

	public String addProduct(ProductBean bean);

	public Long getSizeByCondition(ProductBean bean);

	public String updateProduct(ProductBean bean);
}
