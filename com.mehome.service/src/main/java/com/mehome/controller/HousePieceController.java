package com.mehome.controller;

import com.mehome.domain.SupplierList;
import com.mehome.enumDTO.RoleEnum;
import com.mehome.requestDTO.SupplierBean;
import com.mehome.requestDTO.SupplierRequestDTO;
import com.mehome.service.iface.ISupplierService;
import com.mehome.utils.Permits;
import com.mehome.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/house/piece")
public class HousePieceController {
    @Value("${cros}")
    private String cros;
    @Autowired
    private ISupplierService supplierSerive;

    //根据订单号查询
    @PostMapping("/list")
    @ResponseBody
    public ResponseEntity<Result> list(@RequestBody SupplierBean bean) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(supplierSerive.getListByCondition(bean), supplierSerive.getSizeByCondition(bean)));
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
     * 保存供应商信息
     *
     * @param bean
     * @return
     */
    @Permits(role = {RoleEnum.SUPPLIER})
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
