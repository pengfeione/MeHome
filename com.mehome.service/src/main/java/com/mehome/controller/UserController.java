package com.mehome.controller;

import com.mehome.domain.CompanyList;
import com.mehome.domain.SmsRecord;
import com.mehome.domain.UserInfo;
import com.mehome.requestDTO.UserApplyCompanyDTO;
import com.mehome.requestDTO.UserBackPasswordDTO;
import com.mehome.service.iface.IAliyuncsSMSService;
import com.mehome.service.iface.IUserInfoService;
import com.mehome.utils.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by renhui on 2017/5/8.
 */
@RestController
@RequestMapping("/api/usr")
public class UserController {
    @Value("${cros}")
    private String cros;
    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private IAliyuncsSMSService aliyuncsSMSService;

    /**
     * 用户注册
     *
     * @param userInfo
     * @return
     */
    @PostMapping("/mobile_register")
    @ResponseBody
    public ResponseEntity<Result> mobile_register(@RequestBody UserInfo userInfo) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(userInfoService.mobile_register(userInfo)));
    }

    /**
     * 用户登录
     *
     * @param request
     * @return
     */
    @PostMapping("/mobile_login")
    @ResponseBody
    public ResponseEntity<Result> mobile_login(HttpServletRequest
                                                       request,
                                               @RequestBody UserInfo userInfo) {
        UserInfo result = userInfoService.login(userInfo);
        if (null != result) {
            HttpSession session = request.getSession();
            session.setAttribute("normal", result);
        }
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(result));
    }

    /**
     * 找回密码
     *
     * @param userBackPasswordDTO
     * @return
     */
    @PostMapping("/back_password")
    @ResponseBody
    public ResponseEntity<Result> back_password(@RequestBody UserBackPasswordDTO userBackPasswordDTO) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(userInfoService.backPassword(userBackPasswordDTO)));
    }

    /**
     * 获取注册验证码（注册，找回密码）
     *
     * @param smsRecord
     * @return
     */
    @PostMapping("/verification_code")
    @ResponseBody
    public ResponseEntity<Result> verification_code(@RequestBody SmsRecord smsRecord) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(aliyuncsSMSService.verifyCode(smsRecord)));
    }

    /**
     * 绑定手机号
     *
     * @param userInfo
     * @return
     */
    @PostMapping("/update_info")
    @ResponseBody
    public ResponseEntity<Result> bind_phone(@RequestBody UserInfo userInfo) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(userInfoService.updateRequired(userInfo)));
    }

    /**
     * 绑定手机号
     *
     * @param userApplyCompanyDTO
     * @return
     */
    @PostMapping("/apply_company")
    @ResponseBody
    public ResponseEntity<Result> apply_company(@RequestBody UserApplyCompanyDTO userApplyCompanyDTO) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(userInfoService.applyCompany(userApplyCompanyDTO)));
    }

    /**
     * 获取用户信息
     *
     * @param userInfo
     * @return
     */
    @PostMapping("/get")
    @ResponseBody
    public ResponseEntity<Result> get(@RequestBody UserInfo userInfo) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(userInfoService.selectById(userInfo.getUserId())));
    }
    /**
     * 获取用户信息
     * @param userIds
     * @return
     */
    @PostMapping("/batch_info")
    @ResponseBody
    public ResponseEntity<Result> get(@RequestParam("userIds")String userIds) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(userInfoService.batch_info(userIds)));
    }
}
