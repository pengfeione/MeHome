package com.mehome;

import com.alibaba.fastjson.JSONObject;
import com.mehome.requestDTO.CompanyWelfareNotice;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2017/5/18.
 */
public class TestRandom {
    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            System.out.println(Integer.MAX_VALUE);
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("notice", "adfa");
        jsonObject.put("ab", "bc");
        CompanyWelfareNotice c =
                JSONObject.parseObject(jsonObject.toJSONString(), CompanyWelfareNotice.class);
        System.out.println(c);






    }

    public static int getRandNum(int min, int max) {
        int randNum = min + (int) (Math.random() * ((max - min) + 1));
        return randNum;
    }

    public static String verifyCode() {
        return new DecimalFormat("000000").format((1 + (int) (Math.random() * ((999999 - 1) + 1))));
    }
}
