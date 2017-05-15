package com.mehome.controller;

import com.mehome.domain.CompanyList;
import com.mehome.domain.SmsRecord;
import com.mehome.domain.UserInfo;
import com.mehome.requestDTO.UserBackPasswordDTO;
import com.mehome.service.iface.IUserInfoService;
import com.mehome.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 用户登录
     *
     * @param companyList
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Result> company_add(@RequestBody CompanyList companyList) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(new Object()));
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
     * 用户注册
     *
     * @param companyList
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Result> reg(@RequestBody CompanyList companyList) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(new Object()));
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
                .body(Result.build().content(new Object()));
    }

    /**
     * 获取用户信息
     *
     * @param companyList
     * @return
     */
    @PostMapping("/get")
    @ResponseBody
    public ResponseEntity<Result> get(@RequestBody CompanyList companyList) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(new Object()));
    }
}
