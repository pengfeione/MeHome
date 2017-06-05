package com.mehome.service.iface;

import java.util.List;

import com.mehome.domain.ProductList;
import com.mehome.requestDTO.ProductBean;

public interface IProductService {
    public List<ProductBean> getListByCondition(ProductBean bean);


    public ProductList selectById(Integer productId);

    public String addProduct(ProductBean bean);

    public Long getSizeByCondition(ProductBean bean);

    public String updateProduct(ProductList bean);
}
