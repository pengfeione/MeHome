package com.mehome.dao;

import com.mehome.domain.AuthorizeAdmin;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public interface AuthorizeAdminDao {
    int delete(Integer adminId);

    int insert(AuthorizeAdmin record);

    AuthorizeAdmin login(AuthorizeAdmin record);

    int insertRequired(AuthorizeAdmin record);

    AuthorizeAdmin selectById(Integer adminId);

    int updateRequired(AuthorizeAdmin record);

    AuthorizeAdmin selectByCompanyId(Integer companyId);

}