package com.mehome.dao;

import com.mehome.domain.UserInfo;

public interface UserInfoDao {
    int delete(Integer userId);

    int insert(UserInfo record);

    int insertRequired(UserInfo record);

    UserInfo selectById(Integer userId);

    int updateRequired(UserInfo record);

    int update(UserInfo record);
}