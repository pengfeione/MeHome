package com.mehome.service.impl;

import com.mehome.dao.*;
import com.mehome.domain.AuthorizeAdmin;
import com.mehome.domain.AuthorizeLoginRecord;
import com.mehome.exceptions.InfoException;
import com.mehome.resonpseDTO.AdministratorBean;
import com.mehome.service.iface.IAuthorizeService;
import com.mehome.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/10.
 */
@Service("IAuthorizeService")
public class AuthorizeServiceImpl implements IAuthorizeService {
    @Autowired
    private AuthorizeAdminDao authorizeAdminDao;
    @Autowired
    private AuthorizeRoleMenuDao roleMenuDao;
    @Autowired
    private AuthorizeRolePathDao rolePathDao;
    @Autowired
    private AuthorizeLoginRecordDao loginRecordDao;

    @Override
    public AdministratorBean login(String userName, String password) {
        if (StringUtils.isNull(userName) || StringUtils.isNull(password)) {
            throw new InfoException("用户名或密码不能为空！");
        }
        //查询登录成功的后的信息
        AuthorizeAdmin admin = authorizeAdminDao.login(new AuthorizeAdmin(userName, password));
        if (null != admin) {
            AdministratorBean result = new AdministratorBean();
            //登录成功
            if (StringUtils.isNotNull(admin.getRole())) {
                //查询最后一次登录时间
                AuthorizeLoginRecord record = loginRecordDao.selectById(admin.getAdminId());
                if (null != record) {
                    result.setLastLoginTime(record.getLastLoginTime());
                } else {
                    result.setLastLoginTime(null);
                }
                //查询可访问的路径
                result.setPaths(rolePathDao.listByRole(admin.getRole()));
                //查询可见菜单
                result.setMenus(roleMenuDao.listByRole(admin.getRole()));
            }
            return result;
        } else {
            throw new InfoException("用户名或密码不正确！");
        }
    }
}
