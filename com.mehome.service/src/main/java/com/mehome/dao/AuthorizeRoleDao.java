package com.mehome.dao;

import com.mehome.domain.AuthorizeRole;

public interface AuthorizeRoleDao {
    int delete(String role);

    int insert(AuthorizeRole record);

    int insertRequired(AuthorizeRole record);

    AuthorizeRole selectById(String role);

    int updateRequired(AuthorizeRole record);

    int update(AuthorizeRole record);
}