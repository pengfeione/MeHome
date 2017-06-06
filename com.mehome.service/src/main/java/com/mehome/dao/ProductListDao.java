package com.mehome.dao;

import java.util.List;

import com.mehome.domain.CompanyWelfare;
import com.mehome.domain.OrderList;
import com.mehome.domain.ProductList;
import com.mehome.requestDTO.OrderBean;
import com.mehome.requestDTO.ProductBean;
import org.apache.ibatis.annotations.Param;

public interface ProductListDao {
    int delete(Integer productId);

    int insert(ProductList record);

    int insertRequired(ProductList record);

    ProductList selectById(Integer productId);

    int updateRequired(ProductList record);

    int update(ProductList record);

    public List<ProductList> getListByCondition(ProductBean bean);

    Long getSizeByCondition(ProductBean bean);


}