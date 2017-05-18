package com.mehome.service.iface;

import com.mehome.domain.SmsRecord;
import com.mehome.utils.AliyuncsSMSTemp;

/**
 * Created by pengfei on 2017/5/15.
 */
public interface IAliyuncsSMSService {
    /**
     * 发送阿里消息
     *
     * @param aliyuncsSMSTemp
     */
    public void SendMsg(AliyuncsSMSTemp aliyuncsSMSTemp);

    /**
     * 找回或更新密码
     *
     * @param smsRecord
     * @return
     */
    public String verifyCode(SmsRecord smsRecord);


}
