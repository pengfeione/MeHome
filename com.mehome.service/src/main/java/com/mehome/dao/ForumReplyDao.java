package com.mehome.dao;

import com.mehome.domain.ForumReply;

public interface ForumReplyDao {
    int delete(String id);

    int insert(ForumReply record);

    int insertRequired(ForumReply record);

    ForumReply selectById(String id);

    int updateRequired(ForumReply record);

    int updateByPrimaryKeyWithBLOBs(ForumReply record);

    int update(ForumReply record);
}