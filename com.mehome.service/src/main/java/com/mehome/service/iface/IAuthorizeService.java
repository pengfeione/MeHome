package com.mehome.service.iface;

import com.mehome.domain.AuthorizeAdmin;
import com.mehome.resonpseDTO.AdministratorBean;

/**
 * Created by Administrator on 2017/5/10.
 */
public interface IAuthorizeService {
    /**
     * 管理员进行登录
     *
     * @param userName 用户名
     * @param password 密码
     * @param loginIp  登录ip
     * @param headers  登录的头部信息
     * @return
     */
    public AdministratorBean login(String userName, String password, String loginIp, String headers);

    /**
     * 某一个路径是否允许
     *
     * @param path
     * @param companyAdminId
     * @return
     */
    public boolean permitCompanyPath(String path, Integer companyAdminId);

    /**
     * 获取管理员的角色
     *
     * @return
     */
    public AuthorizeAdmin getAdminInfoById(Integer adminId);
}
