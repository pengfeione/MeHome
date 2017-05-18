package com.mehome.enumDTO;

/**
 * Created by Administrator on 2017/5/12.
 */
public enum RoleEnum {
    USER("user"), COMPANY("company"), SUPER("super"), PLATFORM("platform");
    private String role;

    private RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
