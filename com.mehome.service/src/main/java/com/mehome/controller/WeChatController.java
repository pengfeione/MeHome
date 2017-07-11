package com.mehome.controller;

import com.mehome.domain.UserInfo;
import com.mehome.requestDTO.ProductBean;
import com.mehome.requestDTO.UserBackPasswordDTO;
import com.mehome.service.iface.IOrderService;
import com.mehome.service.iface.IUserInfoService;
import com.mehome.service.weixin.WXResult;
import com.mehome.utils.Result;
import com.mehome.utils.WeChatInfo;
import com.mehome.utils.WeChatTokenService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IOrderService orderService;

    /**
     * 通过url获取微信code
     *
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
     *
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

    /**
     * 根据微信code获取用户信息
     *
     * @param code
     * @return
     */
    @RequestMapping("/auth")
    public ResponseEntity<Result> auth(@RequestParam(value = "code", required = false) String code) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(userInfoService.weChatInfo(weChatInfo.GetWeixinInfo(code))));
    }
    
    @RequestMapping("/notify")
    public ResponseEntity<WXResult> notify(HttpServletRequest req, HttpServletResponse resp) {
    	orderService.payNotify();
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(WXResult.build());
    }
}