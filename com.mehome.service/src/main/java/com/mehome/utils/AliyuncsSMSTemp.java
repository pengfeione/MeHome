package com.mehome.utils;

/**
 * Created by pengfei on 2017/5/12.
 */
public class AliyuncsSMSTemp {
    //模版code
    private String smstemplatecode;//模版code 阿里云生成，动态变化的
    private String receiverphonenumber;//发送的手机号码
    private String checkcode;//短信模板变量：模板内容中的变量${number}，手机验证码
    private String checktime;//有效时间
    private String signname;//签名


    public String getSmstemplatecode() {
        return smstemplatecode;
    }

    public void setSmstemplatecode(String smstemplatecode) {
        this.smstemplatecode = smstemplatecode;
    }

    public String getReceiverphonenumber() {
        return receiverphonenumber;
    }

    public void setReceiverphonenumber(String receiverphonenumber) {
        this.receiverphonenumber = receiverphonenumber;
    }

    public String getCheckcode() {
        return checkcode;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    public String getChecktime() {
        return checktime;
    }

    public void setChecktime(String checktime) {
        this.checktime = checktime;
    }

    public String getSignname() {
        return signname;
    }

    public void setSignname(String signname) {
        this.signname = signname;
    }

}

