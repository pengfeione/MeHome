package com.mehome.dao;

import com.mehome.domain.CompanyWelfare;

public interface CompanyWelfareDao {
    int delete(Integer welfareId);

    int insert(CompanyWelfare record);

    int insertRequired(CompanyWelfare record);

    CompanyWelfare selectById(Integer welfareId);

    int updateRequired(CompanyWelfare record);

    int update(CompanyWelfare record);
}