package com.mehome.service.iface;

import java.util.List;

import com.mehome.requestDTO.TopicBean;

public interface ITopicService {
	List<TopicBean> getListByCondition(TopicBean bean);
	
	Long getSizeByCondition(TopicBean bean);
	
	String addTopic(TopicBean bean);

	String updateTopic(TopicBean bean);
}
