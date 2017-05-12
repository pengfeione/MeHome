package com.mehome.service.iface;

import com.mehome.resonpseDTO.AdministratorBean;

/**
 * Created by Administrator on 2017/5/10.
 */
public interface IAuthorizeService {

    public AdministratorBean login(String userName, String password, String loginIp, String headers);
}
