package com.mehome.dao;

import com.mehome.domain.AuthorizeLoginRecord;

public interface AuthorizeLoginRecordDao {
    int delete(Integer adminId);

    int insert(AuthorizeLoginRecord record);

    int insertRequired(AuthorizeLoginRecord record);

    AuthorizeLoginRecord selectById(Integer adminId);

    int updateRequired(AuthorizeLoginRecord record);

    int update(AuthorizeLoginRecord record);

    AuthorizeLoginRecord selectLastLogin(Integer adminId);
}