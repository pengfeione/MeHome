package com.mehome.service.impl;

import com.mehome.dao.CompanyListDao;
import com.mehome.dao.SmsRecordDao;
import com.mehome.dao.UserInfoDao;
import com.mehome.domain.SmsRecord;
import com.mehome.domain.UserInfo;
import com.mehome.enumDTO.SmsEnum;
import com.mehome.enumDTO.UserCompanyEnum;
import com.mehome.exceptions.InfoException;
import com.mehome.requestDTO.UserBackPasswordDTO;
import com.mehome.requestDTO.UserInfoDTO;
import com.mehome.service.iface.IUserInfoService;
import com.mehome.utils.AssertUtils;
import com.mehome.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2017/5/11.
 */
@Service("IUserInfoService")
public class UserInfoServiceImpl implements IUserInfoService {
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private CompanyListDao companyListDao;
    @Autowired
    private SmsRecordDao smsRecordDao;
    @Value("${default_normal_avatar}")
    private String defaultAvatar;

    @Override
    public UserInfo login(UserInfo userInfo) {
        AssertUtils.isNotNull(userInfo.getMobile(), "用户手机号不能为空！");
        AssertUtils.isNotNull(userInfo.getPassword(), "用户密码不能为空！");
        UserInfo result = userInfoDao.login(userInfo);
        if (null != result) {
            return result;
        } else {
            throw new InfoException("用户名或密码错误！");
        }
    }

    @Override
    public int mobile_register(UserInfo userInfo) {
        AssertUtils.isNotNull(userInfo.getMobile(), "手机号不能为空！");
        AssertUtils.isNotNull(userInfo.getPassword(), "密码不能为空！");
        if (StringUtils.isNull(userInfo.getAvatar())) {
            userInfo.setAvatar(defaultAvatar);
        }
        SmsRecord validSms = smsRecordDao.selectValid(userInfo.getMobile(), SmsEnum.NORMAL_REG.getKey());
        if (null == validSms) {
            throw new InfoException("验证码已失效！");
        } else {
            if (!validSms.getCode().equals(userInfo.getVerifyCode())) {
                throw new InfoException("验证码不正确！");
            }
        }
        AssertUtils.isNull(userInfoDao.selectByMobile(userInfo.getMobile()), "该手机号已注册！");
        return userInfoDao.insertRequired(userInfo);
    }

    @Override
    public int backPassword(UserBackPasswordDTO userBackPasswordDTO) {
        AssertUtils.isNotNull(userBackPasswordDTO.getMobile(), "手机号不能为空！");
        AssertUtils.isNotNull(userBackPasswordDTO.getVerifyCode(), "验证码不能为空！");
        AssertUtils.isNotNull(userBackPasswordDTO.getPassword(), "新密码不能为空！");
        SmsRecord validSms = smsRecordDao.selectValid(userBackPasswordDTO.getMobile(), SmsEnum.NORMAL_FIX_PASSWORD.getKey());
        if (null == validSms) {
            throw new InfoException("验证码已失效！");
        } else {
            if (!validSms.getCode().equals(userBackPasswordDTO.getVerifyCode())) {
                throw new InfoException("验证码不正确！");
            }
        }
        return userInfoDao.updatePasswordByMobile(new UserInfo(userBackPasswordDTO.getMobile(), userBackPasswordDTO.getPassword()));
    }

    @Override
    public List<UserInfo> listByCondition(UserInfoDTO userInfoDTO) {
        return userInfoDao.listByCondition(userInfoDTO);
    }

    @Override
    public long authNum(Integer companyId) {
        AssertUtils.isNotNull(companyId, "企业ID不能为空！");
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setCompanyId(String.valueOf(companyId));
        userInfoDTO.setCompanyStatus(UserCompanyEnum.ACTIVE.getKey());
        return countByCondition(userInfoDTO);
    }

    @Override
    public Long countByCondition(UserInfoDTO UserInfoDTO) {
        Long ret = userInfoDao.countByCondition(UserInfoDTO);
        if (null == ret) {
            return 0l;
        } else {
            return ret;
        }
    }

    @Override
    public boolean operation(Integer companyId, Integer userId, Integer operationEnum) {
        AssertUtils.isNotNull(companyId, "企业未知!");
        AssertUtils.isNotNull(userId, "操作用户未知!");
        AssertUtils.isNotNull(operationEnum, "操作未知!");
        boolean isValidOperation = UserCompanyEnum.containKey(operationEnum);
        if (!isValidOperation) {
            throw new InfoException("操作未识别！");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userId);
        userInfo.setCompanyId(companyId);
        userInfo.setCompanyStatus(operationEnum);
        userInfo.setCompanyUpdateTime(Calendar.getInstance().getTime());
        return userInfoDao.updateRequired(userInfo) == 1;
    }

    @Override
    public int updateRequired(UserInfo record) {
        AssertUtils.isNotNull(record.getUserId(), "更新用户无法确定！");
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(record.getUserId());
        userInfo.setMobile(record.getMobile());
        userInfo.setAvatar(record.getAvatar());
        return userInfoDao.updateRequired(record);
    }

    @Override
    public UserInfo selectById(Integer userId) {
        AssertUtils.isNotNull(userId, "用户标识未知");
        return userInfoDao.selectById(userId);
    }
}
