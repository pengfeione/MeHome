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


    String selectWelfareByCondition(@Param("welfareIds") List<Integer> welfareIds, @Param("companyId") Integer companyId);

    List<CompanyWelfare> selectByCompanyId(Integer companyId);


    List<Integer> selectActiveIdByCompanyId(Integer companyId);

    /**
     * 一个企业福利可以被多个产品关联
     *
     * @param productCompanyWelfareDTO
     * @return
     */

    List<CompanyWelfare> listAllCompanyWelfare(ProductCompanyWelfareDTO productCompanyWelfareDTO);

    /**
     * 不列出参数中的公司的，且是未被选择的企业福利
     *
     * @return
     */
    List<CompanyWelfare> listOppositeUnSelected(ProductCompanyWelfareDTO productCompanyWelfareDTO);

    Long countUnSelected();

    int updateRequired(CompanyWelfare record);

    int updateBatchSelectedStatus(@Param("welfareIds") List<String> welfareIds);

    int updateBatchUnSelectStatus(@Param("welfareIds") List<String> welfareIds);

    int update(CompanyWelfare record);
}