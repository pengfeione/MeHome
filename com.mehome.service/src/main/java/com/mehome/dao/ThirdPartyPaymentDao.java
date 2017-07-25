package com.mehome.dao;

import java.util.List;

import com.mehome.domain.ThirdPartyPayment;
import com.mehome.requestDTO.ThirdPayMentBean;

public interface ThirdPartyPaymentDao {
    int delete(String paymentId);

    int insert(ThirdPartyPayment record);

    int insertRequired(ThirdPartyPayment record);

    ThirdPartyPayment selectById(String paymentId);

    int updateRequired(ThirdPartyPayment record);

    int updateByPrimaryKeyWithBLOBs(ThirdPartyPayment record);

    int update(ThirdPartyPayment record);
    
    List<ThirdPartyPayment> selectByCondition(ThirdPayMentBean bean);
    
    Long countByCondition(ThirdPayMentBean bean);
}