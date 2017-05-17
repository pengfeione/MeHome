package com.mehome;

import com.mehome.service.impl.AliyuncsSMSService;
import com.mehome.utils.AliyuncsSMSTemp;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by pengfei on 2017/5/15.
 */
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
@Component
public class TestSend {
    @Autowired
    public AliyuncsSMSService aliyuncsSMSService;

    @Test
    public void SendMsg() {
        /**
         * 注册消息	模版code=SMS_67195847
         找回密码	模版code=SMS_67245574
         */
        AliyuncsSMSTemp aliyuncsSMSTemp = new AliyuncsSMSTemp();
        aliyuncsSMSTemp.setCheckcode("123456");
        aliyuncsSMSTemp.setChecktime("10");
        aliyuncsSMSTemp.setReceiverphonenumber("18501640340");
        aliyuncsSMSTemp.setSmstemplatecode("SMS_66935087");//
        aliyuncsSMSTemp.setSignname("彭飞1测试号");//签名
        aliyuncsSMSService.SendMsg(aliyuncsSMSTemp);
        System.out.println("2222");
    }
}
