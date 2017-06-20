package com.mehome.dao;

import com.mehome.domain.AuthorizeAdmin;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public interface AuthorizeAdminDao {
    int delete(Integer adminId);

    AuthorizeAdmin login(AuthorizeAdmin record);

    int insertRequired(AuthorizeAdmin record);

    AuthorizeAdmin selectById(Integer adminId);

    int updateRequired(AuthorizeAdmin record);

    AuthorizeAdmin selectByCompanyId(Integer companyId);

    AuthorizeAdmin selectBySupplierId(Integer supplierId);

    AuthorizeAdmin selectByName(String adminName);


}