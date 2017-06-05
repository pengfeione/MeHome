package com.mehome.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.mehome.dao.SupplierListDao;
import com.mehome.domain.SupplierList;
import com.mehome.utils.AssertUtils;
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
    private SupplierListDao supplierListDao;
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
                Integer productId = product.getProductId();
                BasicBean basic = new BasicBean();
                basic.setProductId(productId);
                List<ProductRalationBasic> relationList = productRalationBasicDAO.getListByCondition(basic);
                List<BasicBean> basicBeanList = new ArrayList<BasicBean>();
                if (relationList != null && relationList.size() > 0) {
                    for (ProductRalationBasic productRalationBasic : relationList) {
                        Integer basicId = productRalationBasic.getBasicId();
                        BasicFacilities basicObj = basicFacilitiesDAO.selectById(basicId);
                        BasicBean newBean = new BasicBean(basicObj);
                        basicBeanList.add(newBean);
                    }
                }
                ProductBean newBean = new ProductBean(product, null, basicBeanList, null, null);
                if (null != newBean && null != newBean.getSupplierId()) {
                    SupplierList supplierList = supplierListDao.selectById(newBean.getSupplierId());
                    if (null != supplierList) {
                        newBean.setSupplierName(supplierList.getSupplierName());
                    } else {
                        newBean.setSupplierName(String.valueOf(newBean.getSupplierId()));
                    }
                }
                productBeanList.add(newBean);
            }
        }
        return productBeanList;
    }


    @Override
    public ProductList selectById(Integer productId) {
        return productListDAO.selectById(productId);
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
    public String updateProduct(ProductList bean) {
        AssertUtils.isNotNull(bean.getProductId(), "更新标识未知");
        try {
            int row = productListDAO.updateRequired(bean);
        } catch (Exception e) {
            log.error("更新产品出错:" + e);
            return Boolean.FALSE.toString();
        }
        return Boolean.TRUE.toString();
    }
}
