package com.mehome.dao;

import com.mehome.domain.ThirdPartyPayment;

public interface ThirdPartyPaymentDao {
    int delete(String paymentId);

    int insert(ThirdPartyPayment record);

    int insertRequired(ThirdPartyPayment record);

    ThirdPartyPayment selectById(String paymentId);

    int updateRequired(ThirdPartyPayment record);

    int updateByPrimaryKeyWithBLOBs(ThirdPartyPayment record);

    int update(ThirdPartyPayment record);
}