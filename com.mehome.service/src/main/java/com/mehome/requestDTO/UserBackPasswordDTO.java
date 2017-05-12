package com.mehome.requestDTO;

/**
 * Created by Administrator on 2017/5/11.
 */
public class UserBackPasswordDTO {
    private String mobile;
    private String verifyCode;
    private String password;

    public UserBackPasswordDTO() {
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
