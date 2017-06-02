package com.mehome.dao;

import com.mehome.domain.UserInfo;
import com.mehome.requestDTO.BatchUserRequestDTO;
import com.mehome.requestDTO.UserInfoDTO;

import java.util.List;

public interface UserInfoDao {
    int delete(Integer userId);

    int insertRequired(UserInfo record);

    UserInfo selectById(Integer userId);


    UserInfo selectByOpen(UserInfo record);


    UserInfo selectByMobile(String mobile);

    int updateRequired(UserInfo record);

    int updatePasswordByMobile(UserInfo record);


    List<UserInfo> listByCondition(UserInfoDTO userInfoDTO);


    Long countByCondition(UserInfoDTO userInfoDTO);


    UserInfo login(UserInfo record);


    List<UserInfo> batch_info(BatchUserRequestDTO batchUserRequestDTO);
}