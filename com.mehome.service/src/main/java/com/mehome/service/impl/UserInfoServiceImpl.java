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
    public List<UserInfo> listByCondition(UserInfoDTO userInfoDTO) {
        if (StringUtils.isNotNull(userInfoDTO.getCompanyName())) {
            List<String> companyList = userInfoDTO.getCompanyIdList();
            List<String> nameCompanyIdList = companyListDao.listIdsByName(userInfoDTO.getCompanyName());
            if (null == companyList) {
                companyList = new ArrayList<String>();
                companyList.addAll(nameCompanyIdList);
                userInfoDTO.setCompanyIdList(companyList);
            } else {
                companyList.addAll(nameCompanyIdList);
            }
        }
        return userInfoDao.listByCondition(userInfoDTO);
    }

    @Override
    public long authNum(Integer companyId) {
        AssertUtils.isNotNull(companyId, "企业ID不能为空！");
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setCompanyIds(String.valueOf(companyId));
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
}
