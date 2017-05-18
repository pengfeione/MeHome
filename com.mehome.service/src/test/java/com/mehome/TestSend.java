package com.mehome;

import com.mehome.service.impl.AliyuncsSMSService;
import com.mehome.utils.AliyuncsSMSTemp;
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

@RunWith(SpringRunner.class)
@SpringBootTest
@Component
public class TestSend {
    @Autowired
    public AliyuncsSMSService aliyuncsSMSService;
    @Test
    public void SendMsg() {
        //密码找回	67315188	SMS_67245574
        //注册短信	67275188	SMS_67195847
        AliyuncsSMSTemp aliyuncsSMSTemp =new AliyuncsSMSTemp();

        aliyuncsSMSTemp.setCheckcode("123456");
        aliyuncsSMSTemp.setChecktime("10");
        aliyuncsSMSTemp.setReceiverphonenumber("18501640340");
        aliyuncsSMSTemp.setSmstemplatecode("SMS_67245574");
        aliyuncsSMSTemp.setSignname("米家公寓");
        aliyuncsSMSService.SendMsg(aliyuncsSMSTemp);

        System.out.println("2222");
    }
}
