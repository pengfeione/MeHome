package com.mehome.controller;

import com.mehome.requestDTO.ProductBean;
import com.mehome.requestDTO.UserBackPasswordDTO;
import com.mehome.utils.Result;
import com.mehome.utils.WeChatInfo;
import com.mehome.utils.WeChatTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by pengfei on 2017/5/17.
 */

@RestController
@RequestMapping("/api/wechat")
public class WeChatController {
    @Value("${cros}")
    private String cros;
    @Autowired
    private WeChatInfo weChatInfo;

    /**
     * 通过url获取微信code
     * @param url
     * @return
     */
    @GetMapping("/code")
    @ResponseBody
    public ResponseEntity<Result> getCode(String url) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(weChatInfo.getRequestCodeUrl(url)));

    }

    /**
     * 通过code获取微信用户信息
     * @param code
     * @return
     */
    @GetMapping("/wechatinfo")
    @ResponseBody
    public ResponseEntity<Result> WechatInfo(String code) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(weChatInfo.GetWeixinInfo(code)));

    }
}