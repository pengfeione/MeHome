package com.mehome.service.impl;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.mehome.resonpseDTO.AliyunOssToken;
import com.mehome.service.iface.IAliYunService;
import com.mehome.utils.AliyuncsOSSToken;
import com.mehome.utils.AliyuncsProperties;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/19.
 */
@Service("IAliYunService")
public class AliYunServiceImpl implements IAliYunService {
    @Autowired
    public AliyuncsProperties aliyuncsProperties;

    @Override
    public String getToken() {
        AliyunOssToken aliyuncsOSSToken = new AliyunOssToken();
        String endpoint = aliyuncsProperties.getOss_endpoint();
        String accessId = aliyuncsProperties.getAccessid();
        String accessKey = aliyuncsProperties.getAccessKey();
        String bucket = aliyuncsProperties.getOss_bucket();
        String dir = aliyuncsProperties.getOss_dir();
        String host = "http://" + bucket + "." + endpoint;
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        try {
            long expireTime = 30;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);

            aliyuncsOSSToken.setAccessId(accessId);
            aliyuncsOSSToken.setPolicy(encodedPolicy);
            aliyuncsOSSToken.setSignature(postSignature);
            aliyuncsOSSToken.setDir(dir);
            aliyuncsOSSToken.setHost(host);
            aliyuncsOSSToken.setExpire(String.valueOf(expireEndTime / 1000));
            return "var upload_params=" + aliyuncsOSSToken + ";";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
