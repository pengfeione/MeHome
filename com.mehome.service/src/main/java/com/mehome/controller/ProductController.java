package com.mehome.controller;

import com.mehome.domain.CompanyWelfareDTO;
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
    public ResponseEntity<Result> add(@RequestBody ProductList bean) {
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

    /**
     * 更新个人福利
     *
     * @param productId
     * @return
     */
    @PostMapping("/update_welfare")
    @ResponseBody
    public ResponseEntity<Result> updateWelfare(@RequestBody CompanyWelfareNotice welfare, @RequestParam("productId") Integer productId) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(productService.updateWelfare(welfare, productId)));
    }

    /**
     * 删除个人福利
     *
     * @param productId
     * @return
     */
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
    public ResponseEntity<Result> listCompanyWelfare(@RequestBody ProductCompanyWelfareDTO productCompanyWelfareDTO) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(productService.listCompanyWelfare(productCompanyWelfareDTO), productService.countCompanyWelfare(productCompanyWelfareDTO)));
    }

    @PostMapping("/get_company_welfare")
    @ResponseBody
    public ResponseEntity<Result> getCompanyWelfare(@RequestBody CompanyWelfareDTO companyWelfareDTO) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(productService.getCompanyWelfare(companyWelfareDTO)));
    }

    @PostMapping("/get_company_welfare_by_usr")
    @ResponseBody
    public ResponseEntity<Result> getCompanyWelfareByUser(@RequestBody CompanyWelfareDTO companyWelfareDTO) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(productService.getCompanyWelfare(companyWelfareDTO)));
    }

    @PostMapping("/get_welfare_by_usr")
    @ResponseBody
    public ResponseEntity<Result> getWelfareByUser(@RequestBody CompanyWelfareDTO companyWelfareDTO) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(productService.getWelfare(companyWelfareDTO)));
    }


    @PostMapping("/add_company_welfare")
    @ResponseBody
    public ResponseEntity<Result> addCompanyWelfare(@RequestParam("productId") Integer productId, @RequestParam("companyWelfareIds") String companyWelfareIds) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(productService.addCompanyWelfare(productId, companyWelfareIds)));
    }

    @PostMapping("/delete_company_welfare")
    @ResponseBody
    public ResponseEntity<Result> deleteCompanyWelfare(@RequestParam("productId") Integer productId, @RequestParam("companyWelfareIds") String companyWelfareIds) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(productService.deleteCompanyWelfare(productId, companyWelfareIds)));
    }

    @PostMapping("/list_basic")
    @ResponseBody
    public ResponseEntity<Result> listBasic(@RequestParam("productId") Integer productId) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(productService.getBasicList(productId)));
    }
}
