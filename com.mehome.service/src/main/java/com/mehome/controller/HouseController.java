package com.mehome.controller;

import java.util.List;

import com.mehome.domain.HouseResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mehome.requestDTO.HouseBean;
import com.mehome.service.iface.IHouseService;
import com.mehome.utils.Result;

@RestController
@RequestMapping("/api/house")
public class HouseController {
    @Value("${cros}")
    private String cros;
    @Autowired
    private IHouseService houseService;

    @PostMapping("/list")
    @ResponseBody
    public ResponseEntity<Result> list(@RequestBody HouseBean bean) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(houseService.getListByCondition(bean), houseService.getSizeByCondition(bean)));
    }

    @PostMapping("/get")
    @ResponseBody
    public ResponseEntity<Result> get(@RequestParam("houseId") Integer houseId) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(houseService.selectById(houseId)));
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Result> add(@RequestBody HouseBean bean) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(houseService.addHouse(bean)));
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<Result> update(@RequestBody HouseResource bean) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(houseService.updateHouse(bean)));
    }

    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<Result> save(@RequestBody HouseResource bean) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(houseService.saveHouse(bean)));
    }
}
