package com.mehome.service.iface;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.mehome.domain.BasicFacilities;
import com.mehome.domain.CompanyWelfare;
import com.mehome.domain.CompanyWelfareDTO;
import com.mehome.domain.ProductList;
import com.mehome.requestDTO.CompanyWelfareNotice;
import com.mehome.requestDTO.ProductBean;
import com.mehome.requestDTO.ProductCompanyWelfareDTO;
import com.mehome.resonpseDTO.ProductCompanyWelfare;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

public interface IProductService {
    public List<ProductBean> getListByCondition(ProductBean bean);

    public ProductList selectById(Integer productId);

    public String addProduct(ProductList bean);

    public JSONObject getCompanyWelfare(CompanyWelfareDTO companyWelfareDTO);

    public Long getSizeByCondition(ProductBean bean);

    public String updateProduct(ProductList bean);

    public int updateWelfare(CompanyWelfareNotice companyWelfareNotice, Integer productId);

    public int removeWelfare(Integer productId);

    public List<ProductCompanyWelfare> listCompanyWelfare(ProductCompanyWelfareDTO productCompanyWelfareDTO);

    public Long countCompanyWelfare(ProductCompanyWelfareDTO productCompanyWelfareDTO);

    public int addCompanyWelfare(Integer productId, String companyWelfareIds);

    public int deleteCompanyWelfare(Integer productId, String companyWelfareIds);

    public List<BasicFacilities> getBasicList(Integer productId);
}
