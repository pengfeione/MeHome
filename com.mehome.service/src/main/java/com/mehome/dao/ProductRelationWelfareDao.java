package com.mehome.dao;

import com.mehome.domain.CompanyWelfare;
import com.mehome.domain.ProductRelationWelfare;
import com.mehome.requestDTO.ProductCompanyWelfareDTO;
import com.mehome.resonpseDTO.ProductCompanyWelfare;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductRelationWelfareDao {
    int delete(@Param("welfareId") Integer welfareId, @Param("productId") Integer productId);

    int insert(ProductRelationWelfare record);

    int insertRequired(ProductRelationWelfare record);


    int insertBatch(@Param("welfareIds") List<String> welfareIds, @Param("productId") Integer productId);

    int deleteBatch(@Param("welfareIds") List<String> welfareIds, @Param("productId") Integer productId);


    ProductRelationWelfare selectById(@Param("welfareId") Integer welfareId, @Param("productId") Integer productId);

    List<Integer> selectHasAddCompanyId(@Param("productId") Integer productId);


    List<ProductRelationWelfare> selectByProductId(@Param("productId") Integer productId);

    List<ProductCompanyWelfare> listWelfareByProductId(ProductCompanyWelfareDTO productCompanyWelfareDTO);

    Long countWelfareByProductId(ProductCompanyWelfareDTO productCompanyWelfareDTO);

    int updateRequired(ProductRelationWelfare record);

    int update(ProductRelationWelfare record);
}