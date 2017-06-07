package com.mehome.dao;

import com.mehome.domain.CompanyWelfare;
import com.mehome.requestDTO.ProductCompanyWelfareDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompanyWelfareDao {
    int delete(Integer welfareId);

    int insert(CompanyWelfare record);

    int insertRequired(CompanyWelfare record);

    CompanyWelfare selectById(Integer welfareId);


    List<String> selectByIds(@Param("welfareIds") List<String> welfareIds);

    List<CompanyWelfare> selectByCompanyId(Integer companyId);


    List<CompanyWelfare> listUnSelected(ProductCompanyWelfareDTO productCompanyWelfareDTO);

    Long countUnSelected();

    int updateRequired(CompanyWelfare record);

    int updateBatchSelectedStatus(@Param("welfareIds") List<String> welfareIds);

    int updateBatchUnSelectStatus(@Param("welfareIds") List<String> welfareIds);

    int update(CompanyWelfare record);
}