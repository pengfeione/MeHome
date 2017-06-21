package com.mehome.controller;

import com.mehome.domain.HouseRentPiece;
import com.mehome.domain.SupplierList;
import com.mehome.enumDTO.RoleEnum;
import com.mehome.requestDTO.HouseRentPieceDTO;
import com.mehome.requestDTO.SupplierBean;
import com.mehome.requestDTO.SupplierRequestDTO;
import com.mehome.service.iface.IHouseRentPieceService;
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
    private IHouseRentPieceService pieceService;

    //根据订单号查询
    @PostMapping("/list")
    @ResponseBody
    public ResponseEntity<Result> list(@RequestBody HouseRentPieceDTO bean) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(pieceService.listByCondition(bean), pieceService.countByCondition(bean)));
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<Result> update(@RequestBody HouseRentPiece bean) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(pieceService.updateRequired(bean)));
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
    public ResponseEntity<Result> save(@RequestBody HouseRentPiece bean) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(pieceService.insertRequired(bean)));
    }
}
