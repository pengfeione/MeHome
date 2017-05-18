package com.mehome.dao;

import com.mehome.domain.SmsRecord;
import org.apache.ibatis.annotations.Param;

public interface SmsRecordDao {
    int insertRequired(SmsRecord record);

    SmsRecord selectValid(@Param("mobile") String mobile, @Param("type") Integer type);

    int updateRequired(SmsRecord record);

}