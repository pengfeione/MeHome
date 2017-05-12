package com.mehome.service.impl;

import com.mehome.dao.SmsRecordDao;
import com.mehome.dao.UserInfoDao;
import com.mehome.domain.SmsRecord;
import com.mehome.domain.UserInfo;
import com.mehome.enumDTO.SmsEnum;
import com.mehome.exceptions.InfoException;
import com.mehome.requestDTO.UserBackPasswordDTO;
import com.mehome.requestDTO.UserInfoDTO;
import com.mehome.service.iface.IUserInfoService;
import com.mehome.utils.AssertUtils;
import com.mehome.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */
@Service("IUserInfoService")
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private SmsRecordDao smsRecordDao;

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
    public int backPassword(UserBackPasswordDTO userBackPasswordDTO) {
        AssertUtils.isNotNull(userBackPasswordDTO.getMobile(), "手机号不能为空！");
        AssertUtils.isNotNull(userBackPasswordDTO.getVerifyCode(), "验证码不能为空！");
        SmsRecord smsRecord = smsRecordDao.selectById(userBackPasswordDTO.getMobile(), SmsEnum.NORMAL_FIX_PASSWORD.getKey());
        AssertUtils.isNotNull(smsRecord, "验证码发送失败请重试！");
        if (!userBackPasswordDTO.getVerifyCode().equals(smsRecord.getCode())) {
            return userInfoDao.updatePasswordByMobile(new UserInfo(userBackPasswordDTO.getMobile(), userBackPasswordDTO.getPassword()));
        } else {
            throw new InfoException("验证码不正确");
        }
    }

    @Override
    public List<UserInfoDTO> listByCondition(UserInfoDTO UserInfoDTO) {


        return null;
    }
}
