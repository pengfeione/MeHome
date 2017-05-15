package com.mehome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mehome.requestDTO.HouseBean;
import com.mehome.service.iface.IHouseService;
import com.mehome.utils.Result;

@RestController
@RequestMapping("/api/house")
public class HouseController {
	private final static String cros="*";
	@Autowired
	private IHouseService houseService;
	@PostMapping("/list")
	@ResponseBody
	public ResponseEntity<Result> list(@RequestBody HouseBean bean){
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(houseService.getListByCondition(bean),houseService.getSizeByCondition(bean)));
    }
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Result> add(@RequestBody HouseBean bean){
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(houseService.addHouse(bean)));
    }
	@PostMapping("/update")
	@ResponseBody
	public ResponseEntity<Result> update(@RequestBody HouseBean bean){
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(houseService.updateHouse(bean)));
    }
}
