package com.mehome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mehome.dao.BasicFacilitiesDao;
import com.mehome.dao.ProductListDao;
import com.mehome.dao.ProductRalationBasicDao;
import com.mehome.domain.BasicFacilities;
import com.mehome.domain.ProductList;
import com.mehome.domain.ProductRalationBasic;
import com.mehome.requestDTO.BasicBean;
import com.mehome.requestDTO.ProductBean;
import com.mehome.service.iface.IProductService;

@Service("IProductService")
public class ProductServiceImpl implements IProductService {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private ProductListDao productListDAO;
	@Autowired
	private ProductRalationBasicDao productRalationBasicDAO;
	@Autowired
	private BasicFacilitiesDao basicFacilitiesDAO;

	@Override
	public List<ProductBean> getListByCondition(ProductBean bean) {
		List<ProductList> productList = productListDAO.getListByCondition(bean);
		List<ProductBean> productBeanList = new ArrayList<ProductBean>();
		if (productList != null && productList.size() > 0) {
			for (ProductList product : productList) {
				Integer productId=product.getProductId();
				BasicBean basic=new BasicBean();
				basic.setProductId(productId);
				List<ProductRalationBasic> relationList=productRalationBasicDAO.getListByCondition(basic);
				List<BasicBean> basicBeanList=new ArrayList<BasicBean>();
				if(relationList!=null&&relationList.size()>0){
					for (ProductRalationBasic productRalationBasic : relationList) {
						Integer basicId=productRalationBasic.getBasicId();
						BasicFacilities basicObj=basicFacilitiesDAO.selectById(basicId);
						BasicBean newBean=new BasicBean(basicObj);
						basicBeanList.add(newBean);
					}
				}
				ProductBean newBean = new ProductBean(product, null, basicBeanList, null, null);
				
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
