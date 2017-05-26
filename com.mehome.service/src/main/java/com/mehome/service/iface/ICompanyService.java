package com.mehome.service.iface;

import com.mehome.domain.AuthorizeAdmin;
import com.mehome.domain.CompanyList;
import com.mehome.domain.CompanyWelfare;
import com.mehome.requestDTO.CompanyDTO;
import com.mehome.requestDTO.CompanyWelfareRequestDTO;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */
public interface ICompanyService {

    public List<CompanyList> listByCondition(CompanyDTO companyDTO);

    public int update_company_admin(HttpSession session, AuthorizeAdmin authorizeAdmin);

    public Long countByCondition(CompanyDTO companyDTO);

    public int update(CompanyList company);

    public CompanyList selectById(Integer companyId);


    public int insertRequired(CompanyList record);

    public int add_company_welfare(CompanyWelfare companyWelfare);

    public int update_company_welfare(CompanyWelfare companyWelfare);


    public List<CompanyWelfare> list_company_welfare(CompanyWelfareRequestDTO companyWelfare);

}
