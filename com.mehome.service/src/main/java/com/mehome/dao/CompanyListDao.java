package com.mehome.dao;

import com.mehome.domain.CompanyList;
import com.mehome.requestDTO.CompanyDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyListDao {
    int delete(Integer companyId);

    int insert(CompanyList record);

    int insertRequired(CompanyList record);

    CompanyList selectById(Integer companyId);

    /**
     * 根据授权码查询企业信息
     *
     * @param authCode
     * @return
     */
    CompanyList selectByAuthCode(String authCode);


    int updateRequired(CompanyList record);

    int update(CompanyList record);


    List<Integer> listIdsByName(@Param("name") String name);

    List<CompanyList> listByCondition(CompanyDTO companyDTO);

    Long countByCondition(CompanyDTO companyDTO);
}