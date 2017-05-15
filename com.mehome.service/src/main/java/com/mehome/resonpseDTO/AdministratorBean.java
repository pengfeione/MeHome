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


    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }


    public AdministratorBean() {
    }

    public AdministratorBean(AuthorizeAdmin admin) {
        if (null == admin) {
            this.adminId = admin.getAdminId();
            this.avatar = admin.getAvatar();
            this.nickName = admin.getNickName();
            this.role = admin.getRole();
            this.companyId = admin.getCompanyId();
        }
    }

    private List<AuthorizeMenu> menus = new ArrayList<AuthorizeMenu>();

    private List<String> paths = new ArrayList<String>();

    public List<String> getPaths() {
        return paths;
    }

    public void setPaths(List<String> paths) {
        this.paths = paths;
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

    public List<AuthorizeMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<AuthorizeMenu> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
}
