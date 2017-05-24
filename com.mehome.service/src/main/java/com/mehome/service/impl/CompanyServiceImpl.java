package com.mehome.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.mehome.dao.*;
import com.mehome.domain.AuthorizeAdmin;
import com.mehome.domain.CompanyList;
import com.mehome.domain.CompanyWelfare;
import com.mehome.enumDTO.UserCompanyEnum;
import com.mehome.exceptions.InfoException;
import com.mehome.requestDTO.CompanyDTO;
import com.mehome.requestDTO.CompanyWelfareNotice;
import com.mehome.requestDTO.CompanyWelfareRequestDTO;
import com.mehome.requestDTO.UserInfoDTO;
import com.mehome.service.iface.ICompanyService;
import com.mehome.utils.AssertUtils;
import com.mehome.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
                } else {
                    item.setAdminAccount(authorizeAdmin.getName());
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
        AssertUtils.isNotNull(record.getCompanyName(), "企业名称不能为空！");
        if (StringUtils.isNotNull(record.getAuthCode())) {
            if (null != companyDao.selectByAuthCode(record.getAuthCode())) {
                throw new InfoException("授权码冲突，请重新填写");
            }
        }
        return companyDao.insertRequired(record);
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
    public List<CompanyWelfare> list_company_welfare(CompanyWelfareRequestDTO companyWelfare) {
        AssertUtils.isNotNull(companyWelfare.getCompanyId(), "企业ID不能为空！");
        return companyWelfareDao.selectByCompanyId(companyWelfare.getCompanyId());
    }
}
