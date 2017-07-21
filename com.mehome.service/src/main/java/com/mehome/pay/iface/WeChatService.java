package com.mehome.pay.iface;

import com.alibaba.fastjson.JSONObject;
import com.mehome.utils.WeChatApiProperties;
import com.mehome.utils.XmlUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by trancy on 2017/7/20.
 */
@Service("IWeChatService")
public class WeChatService implements IWeChatService {
    @Autowired
    private WeChatApiProperties weChatProperties;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public JSONObject makeOrder(JSONObject order) {
        System.out.println(XmlUtils.toXML(order));
        List<MediaType> mediaTypes = new ArrayList<MediaType>();
        mediaTypes.add(MediaType.APPLICATION_XML);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setAccept(mediaTypes);
        HttpEntity<String> entity = new HttpEntity<String>(XmlUtils.toXML(order), requestHeaders);
        ResponseEntity<String> resp = restTemplate.exchange(weChatProperties.getMakeOrder(), HttpMethod.POST, entity, String.class);
        String str = resp.getBody();
        try {
            return XmlUtils.toJSON(new String(str.getBytes("ISO-8859-1"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return XmlUtils.toJSON(str);
        }
    }
}
