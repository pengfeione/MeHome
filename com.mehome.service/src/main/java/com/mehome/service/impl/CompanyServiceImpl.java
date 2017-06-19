package com.mehome.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.util.TypeUtils;
import com.mehome.dao.*;
import com.mehome.domain.AuthorizeAdmin;
import com.mehome.domain.CompanyList;
import com.mehome.domain.CompanyWelfare;
import com.mehome.enumDTO.RoleEnum;
import com.mehome.enumDTO.UserCompanyEnum;
import com.mehome.exceptions.InfoException;
import com.mehome.requestDTO.CompanyDTO;
import com.mehome.requestDTO.CompanyWelfareNotice;
import com.mehome.requestDTO.CompanyWelfareRequestDTO;
import com.mehome.requestDTO.UserInfoDTO;
import com.mehome.resonpseDTO.AdministratorBean;
import com.mehome.service.iface.ICompanyService;
import com.mehome.utils.AssertUtils;
import com.mehome.utils.RandomUtils;
import com.mehome.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/5/16.
 */
@Service("ICompanyService")
public class CompanyServiceImpl implements ICompanyService {
    @Autowired
    private CompanyListDao companyDao;
    @Autowired
    private CompanyWelfareDao companyWelfareDao;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private AuthorizeAdminDao adminDao;
    @Value("${default_normal_avatar}")
    private String defaultAvatar;

    @Override
    public List<CompanyList> listByCondition(CompanyDTO companyDTO) {
        List<CompanyList> result = companyDao.listByCondition(companyDTO);
        if (result.size() > 0) {
            UserInfoDTO userInfoDTO = new UserInfoDTO();
            userInfoDTO.setCompanyStatus(UserCompanyEnum.ACTIVE.getKey());
            for (CompanyList item : result) {
                userInfoDTO.setCompanyId(String.valueOf(item.getCompanyId()));
                Long authNum = userInfoDao.countByCondition(userInfoDTO);
                AuthorizeAdmin authorizeAdmin = adminDao.selectByCompanyId(item.getCompanyId());
                if (null == authNum) {
                    item.setRegisterNum(0);
                } else {
                    item.setRegisterNum(authNum.intValue());
                }
                if (null == authorizeAdmin) {
                    item.setAdminAccount("");
                    item.setPassword("");
                } else {
                    item.setPassword(authorizeAdmin.getPassword());
                    item.setAdminAccount(authorizeAdmin.getName());
                }
                if (companyWelfareDao.selectByCompanyId(item.getCompanyId()).size() > 0) {
                    item.setHasCompanyWelfare(true);
                } else {
                    item.setHasCompanyWelfare(false);
                }
            }
        }
        return result;
    }

    @Override
    public int update(CompanyList company) {
        AssertUtils.isNotNull(company.getCompanyId(), "更新企业信息标识未知！");
        return companyDao.updateRequired(company);
    }

    @Override
    public Long countByCondition(CompanyDTO companyDTO) {
        return companyDao.countByCondition(companyDTO);
    }

    @Override
    public CompanyList selectById(Integer companyId) {
        if (null == companyId) {
            return null;
        }
        return companyDao.selectById(companyId);
    }

    @Override
    public int insertRequired(CompanyList record) {
        record.setCompanyId(null);
        AssertUtils.isNotNull(record.getCompanyName(), "企业名称不能为空！");
        if (StringUtils.isNotNull(record.getAuthCode())) {
            if (null != companyDao.selectByAuthCode(record.getAuthCode())) {
                throw new InfoException("授权码冲突，请重新填写");
            }
        }
        companyDao.insertRequired(record);
        return record.getCompanyId();
    }

    @Override
    public int add_company_welfare(CompanyWelfare companyWelfare) {
        AssertUtils.isNotNull(companyWelfare.getCompanyId(), "企业ID不能为空！");
        CompanyWelfareNotice companyWelfareNotice = JSONObject.parseObject(companyWelfare.getWelfareContent(), CompanyWelfareNotice.class);
        companyWelfare.setWelfareContent(companyWelfareNotice.toString());
        companyWelfareDao.insertRequired(companyWelfare);
        return companyWelfare.getWelfareId();
    }

    @Override
    public int update_company_welfare(CompanyWelfare companyWelfare) {
        AssertUtils.isNotNull(companyWelfare.getWelfareId(), "企业福利ID不能为空！");
        CompanyWelfareNotice companyWelfareNotice = JSONObject.parseObject(companyWelfare.getWelfareContent(), CompanyWelfareNotice.class);
        if (null != companyWelfareNotice) {
            companyWelfare.setWelfareContent(companyWelfareNotice.toString());
        } else {
            companyWelfare.setWelfareContent(JSONObject.toJSONString(new CompanyWelfareNotice()));
        }
        return companyWelfareDao.updateRequired(companyWelfare);
    }

    @Override
    public List<CompanyWelfare> list_company_welfare(CompanyWelfareRequestDTO companyWelfare) {
        AssertUtils.isNotNull(companyWelfare.getCompanyId(), "企业ID不能为空！");
        return companyWelfareDao.selectByCompanyId(companyWelfare.getCompanyId());
    }

    @Override
    public int update_company_admin(HttpSession session, AuthorizeAdmin authorizeAdmin) {
        AssertUtils.isNotNull(authorizeAdmin.getCompanyId(), "公司ID不能为空！");
        AssertUtils.isNotNull(authorizeAdmin.getName(), "管理员账号不能为空！");
        Object obj = session.getAttribute("user");
        if (null == obj) {
            throw new InfoException("您没有权限进行该操作！");
        }
        AdministratorBean admin = (AdministratorBean) obj;
        CompanyList companyList = companyDao.selectById(authorizeAdmin.getCompanyId());
        if (null == companyList) {
            throw new InfoException("修改的公司不存在");
        }
        AuthorizeAdmin sameCompany = adminDao.selectByCompanyId(authorizeAdmin.getCompanyId());
        if (null == sameCompany) {
            AuthorizeAdmin sameNameAdmin = adminDao.selectByName(authorizeAdmin.getName());
            if (null != sameNameAdmin) {
                throw new InfoException("账号已存在！");
            }
            sameCompany = new AuthorizeAdmin();
            sameCompany.setCompanyId(authorizeAdmin.getCompanyId());
            sameCompany.setAvatar(defaultAvatar);
            sameCompany.setPassword(RandomUtils.password(6));
            sameCompany.setNickName(companyList.getCompanyName());
            sameCompany.setAdminId(admin.getAdminId());
            sameCompany.setRole(RoleEnum.COMPANY.getRole());
            sameCompany.setName(authorizeAdmin.getName());
            adminDao.insertRequired(sameCompany);
            return sameCompany.getAdminId();
        } else {
            throw new InfoException("该公司的管理员已存在！");
        }
    }
}
