package com.mehome.controller;

import com.mehome.domain.SupplierList;
import com.mehome.requestDTO.SupplierRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mehome.requestDTO.SupplierBean;
import com.mehome.service.iface.ISupplierService;
import com.mehome.utils.Result;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {
    @Value("${cros}")
    private String cros;
    @Autowired
    private ISupplierService supplierSerive;

    @PostMapping("/list")
    @ResponseBody
    public ResponseEntity<Result> list(@RequestBody SupplierBean bean) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(supplierSerive.getListByCondition(bean), supplierSerive.getSizeByCondition(bean)));
    }


    @PostMapping("/lists")
    @ResponseBody
    public ResponseEntity<Result> lists(@RequestBody SupplierRequestDTO bean) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(supplierSerive.selectByCondition(bean), supplierSerive.countByCondition(bean)));
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Result> add(@RequestBody SupplierBean bean) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(supplierSerive.addSupplier(bean)));
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<Result> update(@RequestBody SupplierBean bean) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(supplierSerive.updateSupplier(bean)));
    }

    /**
     * @param bean 编辑供应商
     * @return
     * @auther renhui
     */
    @PostMapping("/edit")
    @ResponseBody
    public ResponseEntity<Result> edit(@RequestBody SupplierList bean) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(supplierSerive.updateRequired(bean)));
    }

    /**
     * 保存供应商信息
     *
     * @param bean
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public ResponseEntity<Result> save(@RequestBody SupplierList bean) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(supplierSerive.insertRequired(bean)));
    }
}
