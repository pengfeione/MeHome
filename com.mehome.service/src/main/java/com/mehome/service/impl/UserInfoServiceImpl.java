package com.mehome.service.impl;

import com.mehome.dao.UserInfoDao;
import com.mehome.domain.UserInfo;
import com.mehome.exceptions.InfoException;
import com.mehome.requestDTO.UserInfoDTO;
import com.mehome.service.iface.IUserInfoService;
import com.mehome.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */
@Service("IUserInfoService")
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public UserInfo login(UserInfo userInfo) {
        return userInfoDao.login(userInfo);
    }

    @Override
    public int register(UserInfo userInfo) {
        if (StringUtils.isNull(userInfo.getMobile())) {
            throw new InfoException("手机号不能为空！");
        } else if (StringUtils.isNull(userInfo.getMobile())) {
            throw new InfoException("密码不能为空！");
        }
        return 0;
    }

    @Override
    public List<UserInfoDTO> listByCondition(UserInfoDTO UserInfoDTO) {


        return null;
    }
}
