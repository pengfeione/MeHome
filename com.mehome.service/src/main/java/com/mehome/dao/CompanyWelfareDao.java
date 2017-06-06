package com.mehome.dao;

import com.mehome.domain.CompanyWelfare;

import java.util.List;

public interface CompanyWelfareDao {
    int delete(Integer welfareId);

    int insert(CompanyWelfare record);

    int insertRequired(CompanyWelfare record);

    CompanyWelfare selectById(Integer welfareId);

    List<CompanyWelfare> selectByCompanyId(Integer companyId);


    List<CompanyWelfare> listUnSelected();

    int updateRequired(CompanyWelfare record);

    int update(CompanyWelfare record);
}