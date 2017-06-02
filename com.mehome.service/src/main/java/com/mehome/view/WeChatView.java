package com.mehome.view;

import com.mehome.domain.UserInfo;
import com.mehome.service.iface.IUserInfoService;
import com.mehome.service.impl.UserInfoServiceImpl;
import com.mehome.utils.WeChatInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/WeChat")
public class WeChatView {
    @Autowired
    private WeChatInfo weChatInfo;
    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping("/auth")
    public String auth(@RequestParam(value = "code", required = false) String code) {
        UserInfo userInfo = userInfoService.weChat_register(weChatInfo.GetWeixinInfo(code));
        return "redirect:http://m.mjiahome.com/index.html?code=" + userInfo.getUserId();
    }
}
