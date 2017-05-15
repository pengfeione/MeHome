package com.mehome.dao;

import com.mehome.domain.AuthorizeAdmin;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

public interface AuthorizeAdminDao {
    int delete(Integer adminId);

    int insert(AuthorizeAdmin record);


    AuthorizeAdmin login(AuthorizeAdmin record);

    int insertRequired(AuthorizeAdmin record);


    @Cacheable(value = "admin")
    AuthorizeAdmin selectById(Integer adminId);

    @CachePut(value = "admin", key = "#record.adminId")
    int updateRequired(AuthorizeAdmin record);

}