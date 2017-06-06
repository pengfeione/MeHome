package com.mehome.dao;

import com.mehome.domain.CompanyWelfare;
import com.mehome.domain.ProductRelationWelfare;
import com.mehome.resonpseDTO.ProductCompanyWelfare;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductRelationWelfareDao {
    int delete(@Param("welfareId") Integer welfareId, @Param("productId") Integer productId);

    int insert(ProductRelationWelfare record);

    int insertRequired(ProductRelationWelfare record);

    ProductRelationWelfare selectById(@Param("welfareId") Integer welfareId, @Param("productId") Integer productId);

    List<ProductRelationWelfare> selectByProductId(@Param("productId") Integer productId);

    List<ProductCompanyWelfare> listWelfareByProductId(@Param("productId") Integer productId);

    int updateRequired(ProductRelationWelfare record);

    int update(ProductRelationWelfare record);
}