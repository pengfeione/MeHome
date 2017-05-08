package com.mehome.dao;

import com.mehome.domain.UserAccountOperation;

public interface UserAccountOperationDao {
    int delete(String operationId);

    int insert(UserAccountOperation record);

    int insertRequired(UserAccountOperation record);

    UserAccountOperation selectById(String operationId);

    int updateRequired(UserAccountOperation record);

    int update(UserAccountOperation record);
}