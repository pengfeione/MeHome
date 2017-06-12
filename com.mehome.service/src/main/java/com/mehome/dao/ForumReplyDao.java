package com.mehome.dao;

import java.util.List;

import com.mehome.domain.ForumReply;
import com.mehome.requestDTO.ReplyBean;

public interface ForumReplyDao {
    int delete(String id);

    int insert(ForumReply record);

    int insertRequired(ForumReply record);

    ForumReply selectById(String id);

    int updateRequired(ForumReply record);

    int updateByPrimaryKeyWithBLOBs(ForumReply record);

    int update(ForumReply record);
    
    List<ForumReply> getListByCondition(ReplyBean bean);
    
    Long getSizeByCondition(ReplyBean bean);
}