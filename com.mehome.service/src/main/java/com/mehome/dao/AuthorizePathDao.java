package com.mehome.dao;

import com.mehome.domain.AuthorizePath;

public interface AuthorizePathDao {
    int delete(Integer pathId);

    int insert(AuthorizePath record);

    int insertRequired(AuthorizePath record);

    AuthorizePath selectById(Integer pathId);

    int updateRequired(AuthorizePath record);

    int update(AuthorizePath record);
}