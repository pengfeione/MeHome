package com.mehome.controller;

import com.mehome.domain.CompanyList;
import com.mehome.utils.Result;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by renhui on 2017/5/8.
 */
@RestController
@RequestMapping("/usr")
public class UserController {
    private final static String cros="*";
    /**
     * 平台企业列表
     * @param companyList
     * @return
     */
    @GetMapping("/login")
    @ResponseBody
    public ResponseEntity<Result> company_add(@RequestBody CompanyList companyList) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build("/usr/login").content(new Object()));
    }

    /**
     * 找回密码
     * @param companyList
     * @return
     */
    @GetMapping("/back_password")
    @ResponseBody
    public ResponseEntity<Result> back_password(@RequestBody CompanyList companyList) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build("/usr/back_password").content(new Object()));
    }
    /**
     * 用户注册
     * @param companyList
     * @return
     */
    @GetMapping("/reg")
    @ResponseBody
    public ResponseEntity<Result> reg(@RequestBody CompanyList companyList) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build("/usr/reg").content(new Object()));
    }
    /**
     * 获取注册验证码（注册，找回密码）
     * @param companyList
     * @return
     */
    @GetMapping("/verification_code")
    @ResponseBody
    public ResponseEntity<Result> verification_code(@RequestBody CompanyList companyList) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build("/usr/verification_code").content(new Object()));
    }
    /**
     * 获取用户信息
     * @param companyList
     * @return
     */
    @GetMapping("/get")
    @ResponseBody
    public ResponseEntity<Result> get(@RequestBody CompanyList companyList) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build("/usr/get").content(new Object()));
    }
}
