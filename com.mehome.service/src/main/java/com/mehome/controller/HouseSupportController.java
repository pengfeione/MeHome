package com.mehome.controller;

import com.mehome.dao.BasicFacilitiesDao;
import com.mehome.domain.ProductList;
import com.mehome.requestDTO.CompanyWelfareNotice;
import com.mehome.requestDTO.ProductBean;
import com.mehome.requestDTO.ProductCompanyWelfareDTO;
import com.mehome.service.iface.IProductService;
import com.mehome.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/support")
public class HouseSupportController {
    @Value("${cros}")
    private String cros;
    @Autowired
    private BasicFacilitiesDao basicFacilitiesDao;

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<Result> list() {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(basicFacilitiesDao.list()));
    }


}
