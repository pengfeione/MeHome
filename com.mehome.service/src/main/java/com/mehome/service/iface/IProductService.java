package com.mehome.service.iface;

import java.util.List;

import com.mehome.domain.CompanyWelfare;
import com.mehome.domain.ProductList;
import com.mehome.requestDTO.CompanyWelfareNotice;
import com.mehome.requestDTO.ProductBean;
import com.mehome.resonpseDTO.ProductCompanyWelfare;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface IProductService {
    public List<ProductBean> getListByCondition(ProductBean bean);


    public ProductList selectById(Integer productId);

    public String addProduct(ProductBean bean);

    public Long getSizeByCondition(ProductBean bean);

    public String updateProduct(ProductList bean);

    public int updateWelfare(CompanyWelfareNotice companyWelfareNotice, Integer productId);

    public int removeWelfare(Integer productId);

    public List<ProductCompanyWelfare> listCompanyWelfare(Integer productId);

    public int addCompanyWelfare(@Param("productId") Integer productId, @Param("companyWelfareId") Integer companyWelfareId);

    public int deleteCompanyWelfare(@Param("productId") Integer productId, @Param("companyWelfareId") Integer companyWelfareId);
}
