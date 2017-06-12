package com.mehome.dao;

import java.util.List;

import com.mehome.domain.ForumTopic;
import com.mehome.requestDTO.TopicBean;

public interface ForumTopicDao {
    int delete(String tid);

    int insert(ForumTopic record);

    int insertRequired(ForumTopic record);

    ForumTopic selectById(String tid);

    int updateRequired(ForumTopic record);

    int updateByPrimaryKeyWithBLOBs(ForumTopic record);

    int update(ForumTopic record);
    
    List<ForumTopic> getListByCondition(TopicBean bean);
    
    Long getSizeByCondition(TopicBean bean);
}