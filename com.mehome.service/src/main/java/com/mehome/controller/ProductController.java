package com.mehome.controller;

import com.alipay.api.domain.Product;
import com.mehome.domain.CompanyWelfare;
import com.mehome.domain.ProductList;
import com.mehome.enumDTO.RoleEnum;
import com.mehome.requestDTO.CompanyWelfareNotice;
import com.mehome.utils.Permits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mehome.requestDTO.HouseBean;
import com.mehome.requestDTO.ProductBean;
import com.mehome.service.iface.IHouseService;
import com.mehome.service.iface.IProductService;
import com.mehome.utils.Result;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Value("${cros}")
    private String cros;
    @Autowired
    private IProductService productService;

    @PostMapping("/list")
    @ResponseBody
    public ResponseEntity<Result> list(@RequestBody ProductBean bean) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(productService.getListByCondition(bean), productService.getSizeByCondition(bean)));
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Result> add(@RequestBody ProductBean bean) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(productService.addProduct(bean)));
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<Result> update(@RequestBody ProductList bean) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(productService.updateProduct(bean)));
    }

    @PostMapping("/update_welfare")
    @ResponseBody
    public ResponseEntity<Result> updateWelfare(@RequestBody CompanyWelfareNotice welfare, @RequestParam("productId") Integer productId) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(productService.updateWelfare(welfare, productId)));
    }

    @PostMapping("/remove_welfare")
    @ResponseBody
    public ResponseEntity<Result> removeWelfare(@RequestParam("productId") Integer productId) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(productService.removeWelfare(productId)));
    }

    @PostMapping("/list_company_welfare")
    @ResponseBody
    public ResponseEntity<Result> listCompanyWelfare(@RequestParam(value = "productId", required = false) Integer productId) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(productService.listCompanyWelfare(productId)));
    }

    @PostMapping("/add_company_welfare")
    @ResponseBody
    public ResponseEntity<Result> addCompanyWelfare(@RequestParam("productId") Integer productId, @RequestParam("companyWelfareId") Integer companyWelfareId) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(productService.addCompanyWelfare(productId, companyWelfareId)));
    }

    @PostMapping("/delete_company_welfare")
    @ResponseBody
    public ResponseEntity<Result> deleteCompanyWelfare(@RequestParam("productId") Integer productId, @RequestParam("companyWelfareId") Integer companyWelfareId) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(productService.deleteCompanyWelfare(productId, companyWelfareId)));
    }
}
