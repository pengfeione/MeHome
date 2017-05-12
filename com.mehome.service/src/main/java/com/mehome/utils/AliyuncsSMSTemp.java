package com.mehome.utils;

/**
 * Created by pengfei on 2017/5/12.
 */
public class AliyuncsSMSTemp {
    //模版code
    private String smstemplatecode;
    private String receiverphonenumber;
    //短信模板变量：模板内容中的变量${number}，手机验证码
    private String checkcode;
    private String checktime;
    private String signname;


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

