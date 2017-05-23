package com.mehome.dao;

import java.util.List;

import com.mehome.domain.ProductRalationBasic;
import com.mehome.requestDTO.BasicBean;

public interface ProductRalationBasicDao {
    int delete(String ralationId);

    int insert(ProductRalationBasic record);

    int insertRequired(ProductRalationBasic record);

    ProductRalationBasic selectById(String ralationId);

    int updateRequired(ProductRalationBasic record);

    int update(ProductRalationBasic record);
    
    List<ProductRalationBasic> getListByCondition(BasicBean bean);
}