package com.mehome.service.iface;

import com.mehome.domain.CompanyList;
import com.mehome.requestDTO.CompanyDTO;

import java.util.List;

/**
 * Created by Administrator on 2017/5/12.
 */
public interface ICompanyService {

    public List<CompanyList> listByCondition(CompanyDTO companyDTO);

    public Long countByCondition(CompanyDTO companyDTO);

    public int update(CompanyList company);

}
