package com.mehome.dao;

import com.mehome.domain.AuthorizeAdmin;

public interface AuthorizeAdminDao {
    int delete(Integer adminId);

    int insert(AuthorizeAdmin record);

    int insertRequired(AuthorizeAdmin record);

    AuthorizeAdmin selectById(Integer adminId);

    AuthorizeAdmin login(AuthorizeAdmin record);

    int updateRequired(AuthorizeAdmin record);

    int update(AuthorizeAdmin record);
}