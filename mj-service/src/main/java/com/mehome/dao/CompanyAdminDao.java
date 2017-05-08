package com.mehome.dao;

import com.mehome.domain.CompanyAdmin;

public interface CompanyAdminDao {
    int delete(Integer autoId);

    int insert(CompanyAdmin record);

    int insertRequired(CompanyAdmin record);

    CompanyAdmin selectById(Integer autoId);

    int updateRequired(CompanyAdmin record);

    int update(CompanyAdmin record);
}