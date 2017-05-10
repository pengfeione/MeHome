package com.mehome.dao;

import com.mehome.domain.AuthorizePath;

public interface AuthorizePathDao {
    int delete(String path);

    int insert(AuthorizePath record);

    int insertRequired(AuthorizePath record);
}