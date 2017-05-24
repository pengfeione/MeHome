package com.mehome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mehome.requestDTO.RecommendProductBean;
import com.mehome.service.iface.IRecommendService;
import com.mehome.utils.Result;

@RestController
@RequestMapping("/api/recommend")
public class RecommendController {
	@Value("${cros}")
    private String cros ;
    @Autowired
    private IRecommendService recommendService;

    @PostMapping("/list")
    @ResponseBody
    public ResponseEntity<Result> list(@RequestBody RecommendProductBean bean) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(recommendService.getListByCondition(bean), recommendService.getSizeByCondition(bean)));
    }
}
