package com.mehome.dao;

import com.mehome.domain.SmsRecord;
import org.apache.ibatis.annotations.Param;

public interface SmsRecordDao {
    int delete(@Param("mobile") String mobile, @Param("type") Integer type);

    int insert(SmsRecord record);

    int insertRequired(SmsRecord record);

    SmsRecord selectById(@Param("mobile") String mobile, @Param("type") Integer type);

    int updateRequired(SmsRecord record);

    int update(SmsRecord record);
}