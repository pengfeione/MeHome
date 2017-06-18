package com.mehome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mehome.dao.ForumTopicDao;
import com.mehome.domain.ForumTopic;
import com.mehome.requestDTO.TopicBean;
import com.mehome.service.iface.ITopicService;
@Service("ITopicService")
public class TopicServiceImpl implements ITopicService {
	private Logger log = Logger.getLogger(this.getClass());
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
		String tid=bean.getTid();
		if(StringUtils.isBlank(tid)){
			log.error("tid属性为空");
			return Boolean.FALSE.toString();
		}
		TopicBean newbean=new TopicBean();
		ForumTopic topic=forumTopicDao.selectById(tid);
		ForumTopic updateTopic=newbean.compareToPojo(topic,bean);
		int row=forumTopicDao.updateByPrimaryKeyWithBLOBs(updateTopic);
		if(row>0){
			return Boolean.TRUE.toString();
		}
		else{
			return Boolean.FALSE.toString();
		}
	}

}
