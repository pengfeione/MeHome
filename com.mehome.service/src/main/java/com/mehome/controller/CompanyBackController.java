package com.mehome.controller;

import com.mehome.dao.CompanyListDao;
import com.mehome.domain.AuthorizeAdmin;
import com.mehome.domain.CompanyList;
import com.mehome.domain.CompanyWelfare;
import com.mehome.enumDTO.RoleEnum;
import com.mehome.requestDTO.CompanyDTO;
import com.mehome.requestDTO.CompanyUserListDTO;
import com.mehome.requestDTO.CompanyWelfareRequestDTO;
import com.mehome.requestDTO.UserInfoDTO;
import com.mehome.service.iface.ICompanyService;
import com.mehome.service.iface.IUserInfoService;
import com.mehome.utils.Permits;
import com.mehome.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by renhui on 2017/5/8.
 * 公司平台后台接口
 */
@RestController
@RequestMapping("/api/company")
public class CompanyBackController {
    @Value("${cros}")
    private String cros;
    @Autowired
    private ICompanyService companyService;
    @Autowired
    private IUserInfoService userInfoService;

    /**
     * 企业用户列表
     *
     * @param requestDto
     * @return
     */
    @Permits(role = {RoleEnum.COMPANY, RoleEnum.PLATFORM})
    @PostMapping("/users")
    @ResponseBody
    public ResponseEntity<Result> users(@RequestBody UserInfoDTO requestDto) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(userInfoService.listByCondition(requestDto), userInfoService.countByCondition(requestDto)));
    }


    /**
     * 获取企业认证用户的数量
     *
     * @param companyId 企业id
     * @return
     */
    @Permits(role = {RoleEnum.COMPANY, RoleEnum.PLATFORM})
    @PostMapping("/auth_num")
    @ResponseBody
    public ResponseEntity<Result> auth_num(@RequestParam(value = "companyId", required = true) Integer companyId) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(userInfoService.authNum(companyId)));
    }

    /**
     * 企业操作用户的审核（审核通过，审核不通过，注销）
     *
     * @param operation
     * @param companyId
     * @return
     */
    @Permits(role = {RoleEnum.COMPANY, RoleEnum.PLATFORM})
    @PostMapping("/operation")
    @ResponseBody
    public ResponseEntity<Result> operation(@RequestParam(value = "operation") Integer operation,
                                            @RequestParam(value = "companyId") Integer companyId,
                                            @RequestParam(value = "userId") Integer userId) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result
                        .build()
                        .content(userInfoService.operation(companyId, userId, operation)));
    }

    @Permits(role = {RoleEnum.PLATFORM, RoleEnum.COMPANY})
    @PostMapping("/list")
    @ResponseBody
    public ResponseEntity<Result> list(@RequestBody CompanyDTO condition) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result
                        .build()
                        .content(companyService.listByCondition(condition), companyService.countByCondition(condition)));
    }

    @Permits(role = {RoleEnum.PLATFORM})
    @PostMapping("/update")
    @ResponseBody
    public ResponseEntity<Result> updateCompany(@RequestBody CompanyList companyList) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result
                        .build()
                        .content(companyService.update(companyList)));
    }

    @Permits(role = {RoleEnum.PLATFORM})
    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Result> addCompany(@RequestBody CompanyList condition) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result
                        .build()
                        .content(companyService.insertRequired(condition)));
    }

    /**
     * 添加企业福利
     *
     * @param condition
     * @return
     */
    @Permits(role = {RoleEnum.PLATFORM})
    @PostMapping("/add_company_welfare")
    @ResponseBody
    public ResponseEntity<Result> add_company_welfare(@RequestBody CompanyWelfare condition) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result
                        .build()
                        .content(companyService.add_company_welfare(condition)));
    }

    @Permits(role = {RoleEnum.PLATFORM})
    @PostMapping("/update_company_welfare")
    @ResponseBody
    public ResponseEntity<Result> update_company_welfare(@RequestBody CompanyWelfare condition) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result
                        .build()
                        .content(companyService.update_company_welfare(condition)));
    }

    /**
     * 添加企业福利
     *
     * @param condition
     * @return
     */
    @Permits(role = {RoleEnum.PLATFORM})
    @PostMapping("/list_company_welfare")
    @ResponseBody
    public ResponseEntity<Result> list_company_welfare(@RequestBody CompanyWelfareRequestDTO condition) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result
                        .build()
                        .content(companyService.list_company_welfare(condition)));
    }

    /**
     *
     *
     * @param authorizeAdmin
     * @return
     */
    @Permits(role = {RoleEnum.PLATFORM})
    @PostMapping("/update_company_admin")
    @ResponseBody
    public ResponseEntity<Result> update_company_admin(HttpSession session, @RequestBody AuthorizeAdmin authorizeAdmin) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result
                        .build()
                        .content(companyService.update_company_admin(session, authorizeAdmin)));
    }
}
