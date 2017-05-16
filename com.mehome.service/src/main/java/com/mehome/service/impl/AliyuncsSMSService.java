package com.mehome.service.impl;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;
import com.mehome.service.iface.IAliyuncsSMSService;
import com.mehome.service.iface.IAuthorizeService;
import com.mehome.utils.AliyuncsProperties;
import com.mehome.utils.AliyuncsSMSTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by pengfei on 2017/5/11.
 */
@Service("IAliyuncsSMSService")
public class AliyuncsSMSService  implements IAliyuncsSMSService {
    @Autowired
    public AliyuncsProperties aliyuncsProperties;

    public void SendMsg(AliyuncsSMSTemp aliyuncsSMSTemp) {
        /**
         * Step 1. 获取主题引用
         */
        CloudAccount account = new CloudAccount(aliyuncsProperties.getAccessid(), aliyuncsProperties.getAccessKey(), aliyuncsProperties.getSms_endpoint());
        MNSClient client = account.getMNSClient();
        CloudTopic topic = client.getTopicRef(aliyuncsProperties.getSms_topic());
        /**
         * Step 2. 设置SMS消息体（必须）
         *
         * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。
         */
        RawTopicMessage msg = new RawTopicMessage();
        msg.setMessageBody("sms-message");
        /**
         * Step 3. 生成SMS消息属性
         */
        MessageAttributes messageAttributes = new MessageAttributes();
        BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
        // 3.1 设置发送短信的签名（SMSSignName）
//        batchSmsAttributes.setFreeSignName(aliyuncsSMSTemp.getSignname());
        // 3.2 设置发送短信使用的模板（SMSTempateCode）
        batchSmsAttributes.setTemplateCode(aliyuncsSMSTemp.getSmstemplatecode());
        // 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
        BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
        smsReceiverParams.setParam("checkcode", aliyuncsSMSTemp.getCheckcode());
        smsReceiverParams.setParam("checktime", aliyuncsSMSTemp.getChecktime());
        // 3.4 增加接收短信的号码
        batchSmsAttributes.addSmsReceiver(aliyuncsSMSTemp.getReceiverphonenumber(), smsReceiverParams);
        messageAttributes.setBatchSmsAttributes(batchSmsAttributes);
        try {
            /**
             * Step 4. 发布SMS消息
             */
            //消费发布数据库
            TopicMessage ret = topic.publishMessage(msg, messageAttributes);

            System.out.println("MessageId: " + ret.getMessageId());
            System.out.println("MessageMD5: " + ret.getMessageBodyMD5());

        } catch (ServiceException se) {
            System.out.println(se.getErrorCode() + se.getRequestId());
            System.out.println(se.getMessage());
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.close();
    }
}
