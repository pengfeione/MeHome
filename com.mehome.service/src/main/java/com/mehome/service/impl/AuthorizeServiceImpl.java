package com.mehome.service.impl;

import com.mehome.dao.*;
import com.mehome.domain.AuthorizeAdmin;
import com.mehome.domain.AuthorizeAdminMenu;
import com.mehome.domain.AuthorizeAdminPath;
import com.mehome.domain.AuthorizeLoginRecord;
import com.mehome.exceptions.InfoException;
import com.mehome.resonpseDTO.AdministratorBean;
import com.mehome.service.iface.IAuthorizeService;
import com.mehome.utils.AssertUtils;
import com.mehome.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2017/5/10.
 */
@Service("IAuthorizeService")
public class AuthorizeServiceImpl implements IAuthorizeService {
    @Autowired
    private AuthorizeAdminDao authorizeAdminDao;
    @Autowired
    private AuthorizeLoginRecordDao loginRecordDao;
    @Autowired
    private AuthorizePathDao pathDao;
    @Autowired
    private AuthorizeAdminPathDao adminPathDao;

    @Override
    public AdministratorBean login(String userName, String password, String loginIp, String headers) {
        AssertUtils.isNotNull(userName, "用户名或密码不能为空！");
        AssertUtils.isNotNull(password, "用户名或密码不能为空！");
        //查询登录成功的后的信息
        AuthorizeAdmin admin = authorizeAdminDao.login(new AuthorizeAdmin(userName, password));
        if (null != admin) {
            AdministratorBean result = new AdministratorBean(admin);
            //登录成功
            if (StringUtils.isNotNull(admin.getRole())) {
                //查询最后一次登录时间
                AuthorizeLoginRecord record = loginRecordDao.selectLastLogin(admin.getAdminId());
                if (null != record) {
                    result.setLastLoginTime(record.getLastLoginTime());
                    loginRecordDao.update(record);
                } else {
                    result.setLastLoginTime(null);
                }
                record = new AuthorizeLoginRecord();
                record.setLastLoginTime(Calendar.getInstance().getTime());
                record.setAdminId(admin.getAdminId());
                record.setLastIp(loginIp);
                record.setHeaders(headers);
                loginRecordDao.insert(record);
            }
            return result;
        } else {
            return null;
        }
    }

    @Override
    public boolean permitCompanyPath(String path, Integer companyAdminId) {
        //若路径和ID为空
        if (StringUtils.isNull(path) || null == companyAdminId) {
            return false;
        }
        Integer pathId = pathDao.getPathIdByPath(path);
        if (null == pathId) {
            return false;
        }
        AuthorizeAdminPath authorizeAdminPath = adminPathDao.isexits(companyAdminId, pathId);
        if (null == authorizeAdminPath) {
            return false;
        }
        return true;
    }

    @Override
    public AuthorizeAdmin getAdminInfoById(Integer adminId) {
        if (null == adminId) {
            return null;
        }
        AuthorizeAdmin authorizeAdmin = authorizeAdminDao.selectById(adminId);
        authorizeAdminDao.updateRequired(authorizeAdmin);
        return authorizeAdmin;
    }
}
