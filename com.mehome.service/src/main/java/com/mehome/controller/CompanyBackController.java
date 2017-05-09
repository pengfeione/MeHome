package com.mehome.controller;

import com.mehome.requestDTO.CompanyUserListDTO;
import com.mehome.utils.Result;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by renhui on 2017/5/8.
 * 公司平台后台接口
 */
@RestController
@RequestMapping("/company")
public class CompanyBackController {
    private final static String cros="*";
    /**
     * 企业用户列表
     * @param requestDto
     * @return
     */
    @GetMapping("/users")
    @ResponseBody
    public ResponseEntity<Result> users(@RequestBody CompanyUserListDTO requestDto) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build("/company/users").content(new Object()));
    }
    /**
     * 获取企业认证用户的数量
     * @param company 企业id
     * @return
     */
    @GetMapping("/auth_num/{company}")
    @ResponseBody
    public ResponseEntity<Result> auth_num(@PathVariable String company) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build("/platform/auth_num").content(new Object()));
    }
    /**
     * 企业操作用户的审核（审核通过，审核不通过，注销）
     * @param company
     * @return
     */
    @GetMapping("/operation/{company}/{operation}")
    @ResponseBody
    public ResponseEntity<Result> operation(@PathVariable String company) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result
                        .build("/platform/auth_num")
                        .content(new Object()));
    }
}
