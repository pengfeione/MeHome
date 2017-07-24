package com.mehome.dao;

import com.mehome.domain.ForumTopic;
import com.mehome.requestDTO.TopicBean;

import java.util.List;

public interface ForumTopicDao {
    int delete(String tid);

    int insert(ForumTopic record);

    int insertRequired(ForumTopic record);

    ForumTopic selectById(String tid);

    int updateRequired(ForumTopic record);

    int updateByPrimaryKeyWithBLOBs(ForumTopic record);

    int update(ForumTopic record);

    List<ForumTopic> getListByCondition(TopicBean bean);

    List<ForumTopic> getAllActivities(TopicBean bean);

    List<ForumTopic> getAllNormal(TopicBean bean);


    Long getSizeByCondition(TopicBean bean);
}