package com.mehome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mehome.dao.ProductListDao;
import com.mehome.domain.ProductComment;
import com.mehome.domain.ProductList;
import com.mehome.requestDTO.ProductBean;
import com.mehome.service.iface.IProductService;

@Service("IProductService")
public class ProductServiceImpl implements IProductService {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private ProductListDao productListDAO;

	@Override
	public List<ProductBean> getListByCondition(ProductBean bean) {
		List<ProductList> productList = productListDAO.getListByCondition(bean);
		List<ProductBean> productBeanList = new ArrayList<ProductBean>();
		if (productList != null && productList.size() > 0) {
			for (ProductList product : productList) {
				
				ProductBean newBean = new ProductBean(product, null, null, null, null);
				
				productBeanList.add(newBean);
			}
		}
		return productBeanList;
	}

	@Override
	public String addProduct(ProductBean bean) {
		ProductList product = null;
		try {
			product = bean.beanToPojo(Boolean.TRUE);
			productListDAO.insert(product);
		} catch (Exception e) {
			log.error("加入产品出错:" + e);
		}
		return product.getProductId() == null ? "" : product.getProductId().toString();
	}

	@Override
	public Long getSizeByCondition(ProductBean bean) {
		Long size = productListDAO.getSizeByCondition(bean);
		return size;
	}

	@Override
	public String updateProduct(ProductBean bean) {
		ProductList product = null;
		try {
			product = bean.beanToPojo(Boolean.FALSE);
			int row = productListDAO.updateRequired(product);
		} catch (Exception e) {
			log.error("更新产品出错:" + e);
			return Boolean.FALSE.toString();
		}
		return Boolean.TRUE.toString();
	}

}
