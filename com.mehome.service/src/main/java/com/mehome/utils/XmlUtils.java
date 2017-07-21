package com.mehome.utils;

import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by trancy on 2017/7/20.
 */
public class XmlUtils {
    public static JSONObject toJSON(String xml) {
        JSONObject jsonObject = new JSONObject();
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml); // 将字符串转为XML
            Element rootElt = doc.getRootElement();
            Iterator iter = rootElt.elementIterator();
            while (iter.hasNext()) {
                Element recordEle = (Element) iter.next();
                jsonObject.put(recordEle.getName(), recordEle.getText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return jsonObject;
        }
    }

    public static String toXML(JSONObject jsonObject) {
        Document document = DocumentHelper.createDocument();
        Set<String> keySet = jsonObject.keySet();
        Element rootElement = document.addElement("xml");
        for (String key : keySet) {
            Element element = rootElement.addElement(key);
            element.setText(jsonObject.getString(key));
        }
        return formatXml(document,"utf-8", false);
    }

    public static void main(String[] args) {
        String text = "<xml>" +
                "   <appid>wx2421b1c4370ec43b</appid>" +
                "   <attach>支付测试</attach>" +
                "   <body>JSAPI支付测试</body>" +
                "   <mch_id>10000100</mch_id>" +
                "   <detail><![CDATA[{ \"goods_detail\":[ { \"goods_id\":\"iphone6s_16G\", \"wxpay_goods_id\":\"1001\", \"goods_name\":\"iPhone6s 16G\", \"quantity\":1, \"price\":528800, \"goods_category\":\"123456\", \"body\":\"苹果手机\" }, { \"goods_id\":\"iphone6s_32G\", \"wxpay_goods_id\":\"1002\", \"goods_name\":\"iPhone6s 32G\", \"quantity\":1, \"price\":608800, \"goods_category\":\"123789\", \"body\":\"苹果手机\" } ] }]]></detail>" +
                "   <nonce_str>1add1a30ac87aa2db72f57a2375d8fec</nonce_str>" +
                "   <notify_url>http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php</notify_url>" +
                "   <openid>oUpF8uMuAJO_M2pxb1Q9zNjWeS6o</openid>" +
                "   <out_trade_no>1415659990</out_trade_no>" +
                "   <spbill_create_ip>14.23.150.211</spbill_create_ip>" +
                "   <total_fee>1</total_fee>" +
                "   <trade_type>JSAPI</trade_type>" +
                "   <sign>0CB01533B8C1EF103065174F50BCA001</sign>" +
                "</xml>";

        System.out.println(XmlUtils.toJSON(text
        ));
        System.out.println(toXML(XmlUtils.toJSON(text
        )));
    }

    /**
     * 格式化XML文档
     *
     * @param document xml文档
     * @param charset  字符串的编码
     * @param istrans  是否对属性和元素值转义<![CDATA[商品详情，支持html图文混排]]>
     * @return 格式化后XML字符串
     */
    public static String formatXml(Document document, String charset, boolean istrans) {
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding(charset);
        StringWriter sw = new StringWriter();
        XMLWriter xw = new XMLWriter(sw, format);
        xw.setEscapeText(istrans);
        try {
            xw.write(document);
            xw.flush();
            xw.close();
        } catch (IOException e) {
            System.out.println("格式化XML文档发生异常，请检查！");
            e.printStackTrace();
        }
        return sw.toString();
    }
}
