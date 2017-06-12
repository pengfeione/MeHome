package com.mehome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mehome.dao.ForumTopicDao;
import com.mehome.domain.ForumTopic;
import com.mehome.requestDTO.TopicBean;
import com.mehome.service.iface.ITopicService;

public class TopicServiceImpl implements ITopicService {
	@Autowired
	private ForumTopicDao forumTopicDao;
	@Override
	public List<TopicBean> getListByCondition(TopicBean bean) {
		// TODO Auto-generated method stub
		List<ForumTopic> topicList=forumTopicDao.getListByCondition(bean);
		List<TopicBean> beanList=new ArrayList<TopicBean>();
		if(topicList!=null&&topicList.size()>0){
			for (ForumTopic forumTopic : topicList) {
				TopicBean newBean=new TopicBean(forumTopic);
				beanList.add(newBean);
			}
		}
		return beanList;
	}

	@Override
	public Long getSizeByCondition(TopicBean bean) {
		// TODO Auto-generated method stub
		return forumTopicDao.getSizeByCondition(bean);
	}

	@Override
	public String addTopic(TopicBean bean) {
		// TODO Auto-generated method stub
		ForumTopic topic=bean.beanToPojo();
		int row=forumTopicDao.insert(topic);
		if(row>0){
			return Boolean.TRUE.toString();
		}
		else{
			return Boolean.FALSE.toString();
		}
	}

	@Override
	public String updateTopic(TopicBean bean) {
		// TODO Auto-generated method stub
		ForumTopic topic=bean.compareToPojo();
		int row=forumTopicDao.update(topic);
		if(row>0){
			return Boolean.TRUE.toString();
		}
		else{
			return Boolean.FALSE.toString();
		}
	}

}
