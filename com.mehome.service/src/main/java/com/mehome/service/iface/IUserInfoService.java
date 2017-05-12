package com.mehome.service.iface;

import com.mehome.domain.UserInfo;
import com.mehome.requestDTO.UserBackPasswordDTO;
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
     * 修改密码
     *
     * @param userBackPasswordDTO 手机号
     * @return
     */
    public int backPassword(UserBackPasswordDTO userBackPasswordDTO);

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