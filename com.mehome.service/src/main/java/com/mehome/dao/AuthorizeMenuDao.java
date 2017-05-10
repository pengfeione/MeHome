package com.mehome.dao;

import com.mehome.domain.AuthorizeMenu;

public interface AuthorizeMenuDao {
    int delete(Integer menuId);

    int insert(AuthorizeMenu record);

    int insertRequired(AuthorizeMenu record);

    AuthorizeMenu selectById(Integer menuId);

    int updateRequired(AuthorizeMenu record);

    int update(AuthorizeMenu record);
}