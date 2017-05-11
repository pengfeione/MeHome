package com.mehome.dao;

import com.mehome.domain.SmsRecord;

public interface SmsRecordDao {
    int insertRequired(SmsRecord record);

    /**
     * 根据手机号和类型查询验证码
     * @param record
     * @return
     */
    SmsRecord select(SmsRecord record);

    int updateRequired(SmsRecord record);
}