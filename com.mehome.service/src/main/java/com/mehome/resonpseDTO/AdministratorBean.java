package com.mehome.resonpseDTO;

import com.alibaba.fastjson.JSONObject;
import com.mehome.domain.AuthorizeAdmin;
import com.mehome.domain.AuthorizeMenu;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/10.
 */
public class AdministratorBean {
    private Integer adminId;

    private String avatar;

    private String nickName;

    private String role;

    private Date lastLoginTime;

    private int companyId;

    private int supplierId;


    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }


    public AdministratorBean() {
    }

    public AdministratorBean(AuthorizeAdmin admin) {
        if (null != admin) {
            this.adminId = admin.getAdminId();
            this.avatar = admin.getAvatar();
            this.nickName = admin.getNickName();
            this.role = admin.getRole();
            this.companyId = admin.getCompanyId();
            this.supplierId = admin.getSupplierId();
        }
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
