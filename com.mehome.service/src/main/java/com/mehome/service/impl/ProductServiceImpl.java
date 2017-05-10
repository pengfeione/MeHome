package com.mehome.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mehome.requestDTO.ProductBean;
import com.mehome.service.iface.IProductService;
@Service("IProductService")
public class ProductServiceImpl implements IProductService {

	@Override
	public List<ProductBean> getListByCondition(ProductBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addProduct(ProductBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getSizeByCondition(ProductBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateProduct(ProductBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

}
