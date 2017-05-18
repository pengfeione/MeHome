package com.mehome.service.impl;

import com.mehome.dao.AuthorizeAdminDao;
import com.mehome.dao.CompanyListDao;
import com.mehome.dao.UserInfoDao;
import com.mehome.dao.UserReviewDao;
import com.mehome.domain.AuthorizeAdmin;
import com.mehome.domain.CompanyList;
import com.mehome.enumDTO.UserCompanyEnum;
import com.mehome.requestDTO.CompanyDTO;
import com.mehome.requestDTO.UserInfoDTO;
import com.mehome.service.iface.ICompanyService;
import com.mehome.utils.AssertUtils;
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
}
