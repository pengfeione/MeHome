package com.mehome.service.iface;

import com.mehome.domain.UserInfo;
import com.mehome.requestDTO.UserInfoDTO;

import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */
public interface IUserInfoService {
    /**
     * 用户手机号登录
     *
     * @param userInfo
     * @return
     */
    public UserInfo login(UserInfo userInfo);

    /**
     * h5用户注册
     *
     * @param userInfo
     * @return
     */
    public int register(UserInfo userInfo);

    /**
     * 根据条件查询用户信息
     *
     * @param UserInfoDTO
     * @return
     */
    public List<UserInfoDTO> listByCondition(UserInfoDTO UserInfoDTO);
}