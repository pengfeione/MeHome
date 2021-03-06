package com.mehome.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mehome.dao.CompanyListDao;
import com.mehome.dao.SmsRecordDao;
import com.mehome.dao.UserInfoDao;
import com.mehome.dao.UserReviewDao;
import com.mehome.domain.CompanyList;
import com.mehome.domain.SmsRecord;
import com.mehome.domain.UserInfo;
import com.mehome.domain.WeChatUserInfo;
import com.mehome.enumDTO.SmsEnum;
import com.mehome.enumDTO.UserCompanyEnum;
import com.mehome.enumDTO.UserOpenType;
import com.mehome.exceptions.InfoException;
import com.mehome.requestDTO.BatchUserRequestDTO;
import com.mehome.requestDTO.UserApplyCompanyDTO;
import com.mehome.requestDTO.UserBackPasswordDTO;
import com.mehome.requestDTO.UserInfoDTO;
import com.mehome.service.iface.IUserInfoService;
import com.mehome.utils.AssertUtils;
import com.mehome.utils.IdcardValidator;
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
    private CompanyListDao companyDao;
    @Autowired
    private SmsRecordDao smsRecordDao;
    @Autowired
    private UserReviewDao userReviewDao;
    @Value("${default_normal_avatar}")
    private String defaultAvatar;

    @Override
    public UserInfo login(UserInfo userInfo) {
        AssertUtils.isNotNull(userInfo.getMobile(), "用户手机号不能为空！");
        AssertUtils.isNotNull(userInfo.getPassword(), "用户密码不能为空！");
        UserInfo result = userInfoDao.login(userInfo);
        if (null == userInfoDao.selectByMobile(userInfo.getMobile())) {
            throw new InfoException("当前登录手机不存在，马上注册！");
        }
        if (null != result) {
            return result;
        } else {
            throw new InfoException("手机号或密码错误！");
        }
    }

    @Override
    public boolean mobile(String mobile) {
        if (null == userInfoDao.selectByMobile(mobile)) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean selectByAuthCode(String authCode) {
        return companyDao.selectByAuthCode(authCode) == null ? false : true;
    }

    @Override
    public int mobile_register(UserInfo userInfo) {
        AssertUtils.isNotNull(userInfo.getMobile(), "手机号不能为空！");
        AssertUtils.isNotNull(userInfo.getPassword(), "密码不能为空！");
        if (StringUtils.isNull(userInfo.getAvatar())) {
            userInfo.setAvatar(defaultAvatar);
        }
        UserInfo sameMobileUser = userInfoDao.selectByMobile(userInfo.getMobile());
        if (null != sameMobileUser) {
            if (StringUtils.isNull(sameMobileUser.getOpenId())) {
                UserInfo userInfo1 = new UserInfo();
                userInfo1.setMobile(userInfo.getMobile());
                userInfo1.setPassword(userInfo.getPassword());
                if (null == userInfoDao.login(userInfo1)) {
                    throw new InfoException("与该账号首次注册时密码不一致");
                } else {
                    if (StringUtils.isNull(sameMobileUser.getAvatar())) {
                        sameMobileUser.setAvatar(userInfo.getAvatar());
                    }
                    if (StringUtils.isNull(sameMobileUser.getNickName())) {
                        sameMobileUser.setNickName(userInfo.getNickName());
                    }
                    if (!defaultAvatar.equals(sameMobileUser.getAvatar())) {
                        sameMobileUser.setAvatar(userInfo.getAvatar());
                    }
                    sameMobileUser.setSex(userInfo.getSex());
                    sameMobileUser.setOpenId(userInfo.getOpenId());
                    userInfoDao.updateRequired(sameMobileUser);
                    return sameMobileUser.getUserId();
                }
            } else {
                throw new InfoException("该手机号已被别的账号绑定");
            }
        } else {
            AssertUtils.isNull(userInfoDao.selectByMobile(userInfo.getMobile()), "该手机号已注册！");
            SmsRecord validSms = smsRecordDao.selectValid(userInfo.getMobile(), SmsEnum.NORMAL_REG.getKey());
            if (null == validSms) {
                throw new InfoException("验证码已失效！");
            } else {
                if (!validSms.getCode().equals(userInfo.getVerifyCode())) {
                    throw new InfoException("验证码不正确！");
                }
            }
            if (StringUtils.isNotNull(userInfo.getOpenId())) {
                userInfo.setOpenType(UserOpenType.WECHAT.getKey());
            } else {
                userInfo.setOpenType(UserOpenType.MOBILE.getKey());
            }
            userInfo.setCreateTime(Calendar.getInstance().getTime());
            if (StringUtils.isNotNull(userInfo.getOpenId())) {
                UserInfo sameOpenUserInfo = userInfoDao.selectByOpen(userInfo);
                if (null == sameOpenUserInfo) {
                    userInfoDao.insertRequired(userInfo);
                } else {
                    userInfo.setUserId(sameOpenUserInfo.getUserId());
                    sameOpenUserInfo.setMobile(userInfo.getMobile());
                    sameOpenUserInfo.setPassword(userInfo.getPassword());
                    userInfoDao.updateRequired(sameOpenUserInfo);
                }
            } else {
                userInfoDao.insertRequired(userInfo);
            }
            return userInfo.getUserId();
        }
    }

    @Override
    public UserInfo weChatInfo(WeChatUserInfo weChatUserInfo) {
        UserInfo userInfo = new UserInfo();
        userInfo.setOpenId(weChatUserInfo.getOpenid());
        UserInfo existUser = userInfoDao.selectByOpen(userInfo);
        System.out.println("------------------>>>>");
        System.out.println(existUser);
        if (null == existUser) {
            System.out.println("------------------>>>>2");
            System.out.println(existUser);
            userInfo.setAvatar(weChatUserInfo.getHeadimgurl());
            userInfo.setNickName(weChatUserInfo.getNickname());
            userInfo.setSex(Integer.valueOf(weChatUserInfo.getSex()));
            return userInfo;
        } else {
            System.out.println("------------------>>>>3");
            System.out.println(existUser);
            existUser.setPassword("");
            return existUser;
        }
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
        if (StringUtils.isNotNull(record.getMobile()) && null != userInfoDao.selectByMobile(record.getMobile())) {
            throw new InfoException("手机号已被别的用户绑定！");
        }
        userInfo.setMobile(record.getMobile());
        userInfo.setAvatar(record.getAvatar());
        userInfo.setNickName(record.getNickName());
        return userInfoDao.updateRequired(record);
    }

    @Override
    public UserInfo selectById(Integer userId) {
        AssertUtils.isNotNull(userId, "用户标识未知");
        return userInfoDao.selectById(userId);
    }

    @Override
    public boolean applyCompany(UserApplyCompanyDTO userApplyCompanyDTO) {
        AssertUtils.isNotNull(userApplyCompanyDTO.getUserId(), "用户ID不能为空!");
        AssertUtils.isNotNull(userApplyCompanyDTO.getAuthCode(), "授权码不能为空！");
        AssertUtils.isNotNull(userApplyCompanyDTO.getIdCard(), "身份证号不能为空！");
        AssertUtils.isNotNull(userApplyCompanyDTO.getRealName(), "真实姓名不能为空！");
        AssertUtils.isTrue(!IdcardValidator.isValidatedAllIdCard(userApplyCompanyDTO.getIdCard()), "身份证不合法！");

        CompanyList companyList = companyDao.selectByAuthCode(userApplyCompanyDTO.getAuthCode());
        if (null == companyList) {
            throw new InfoException("未找到相关的企业！");
        }
        UserInfo userInfo = userInfoDao.selectById(userApplyCompanyDTO.getUserId());
        if (null == userInfo) {
            throw new InfoException("未找到相关的用户！");
        }
        if (null != userInfo.getCompanyId()
                && userInfo.getCompanyId() != companyList.getCompanyId()
                ) {
            //申请别的企业
            if (UserCompanyEnum.ACTIVE.getKey() == userInfo.getCompanyStatus()) {
                throw new InfoException("您已经有一个申请一个企业了!");
            }
            if (UserCompanyEnum.WAITING.getKey() == userInfo.getCompanyStatus()) {
                throw new InfoException("一个人一次只能申请一个企业！");
            }
        } else {
            if (userInfo.getCompanyId() == companyList.getCompanyId()
                    && (UserCompanyEnum.ACTIVE.getKey() == userInfo.getCompanyStatus() || UserCompanyEnum.WAITING.getKey() == userInfo.getCompanyStatus())) {
                throw new InfoException("不能重复申请！");
            }
        }
        if (null != userInfo.getCompanyId()
                && UserCompanyEnum.DISMISS.getKey() == userInfo.getCompanyStatus()
                && userInfo.getCompanyId() == companyList.getCompanyId()) {
            throw new InfoException("该企业已经拒绝了您的申请！");
        }

        userInfo.setPassword(null);//要不密码会再加密
        userInfo.setRealName(userApplyCompanyDTO.getRealName());
        userInfo.setIdCard(userApplyCompanyDTO.getIdCard());
        userInfo.setCompanyId(companyList.getCompanyId());
        userInfo.setCompanyStatus(UserCompanyEnum.WAITING.getKey());
        userInfo.setCompanyUpdateTime(Calendar.getInstance().getTime());
        userInfo.setCompanyCreateTime(Calendar.getInstance().getTime());
        return userInfoDao.updateRequired(userInfo) == 1;
    }

    @Override
    public Object batch_info(String userIds, String returnType) {
        List<UserInfo> result = null;
        if (StringUtils.isNotNull(userIds)) {
            String[] ids = userIds.split(",");
            if (ids.length > 0) {
                BatchUserRequestDTO batchUserRequestDTO = new BatchUserRequestDTO();
                for (String item : ids) {
                    try {
                        batchUserRequestDTO.addId(Integer.valueOf(item));
                    } catch (NumberFormatException e) {
                    }
                }
                if (batchUserRequestDTO.getSize() > 0) {
                    result = userInfoDao.batch_info(batchUserRequestDTO);
                }
            }
        }
        if ("list".equals(returnType)) {
            if (null != result) {
                return result;
            } else {
                return new ArrayList<UserInfo>();
            }
        } else {
            if (null != result) {
                JSONObject jsonObject = new JSONObject();
                for (UserInfo item : result
                        ) {
                    jsonObject.put(String.valueOf(item.getUserId()), item);
                }
                return jsonObject;
            } else {
                return new JSONObject();
            }
        }
    }
}
