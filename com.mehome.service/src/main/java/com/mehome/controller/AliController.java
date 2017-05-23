package com.mehome.controller;

import com.mehome.enumDTO.RoleEnum;
import com.mehome.requestDTO.UserInfoDTO;
import com.mehome.resonpseDTO.AliyunOssToken;
import com.mehome.service.iface.IAliYunService;
import com.mehome.utils.Permits;
import com.mehome.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/5/19.
 */

@RestController
@RequestMapping("/api/aliyun")
public class AliController {
    @Value("${cros}")
    private String cros;
    @Autowired
    private IAliYunService aliYunService;

    /**
     * 获取阿里云上传文件的token
     *
     * @return
     */
    @GetMapping("/oss_token")
    @ResponseBody
    public ResponseEntity<String> users() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Access-Control-Allow-Origin", "*");
        httpHeaders.add("Access-Control-Allow-Methods", "GET, POST");
        return ResponseEntity
                .ok()
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(aliYunService.getToken());
    }
}
