package com.mehome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mehome.dao.ForumReplyDao;
import com.mehome.domain.ForumReply;
import com.mehome.requestDTO.ReplyBean;
import com.mehome.service.iface.IReplyService;

public class ReplyServiceImpl implements IReplyService {

	@Autowired
	private ForumReplyDao  forumReplyDao;
	@Override
	public List<ReplyBean> getListByCondition(ReplyBean bean) {
		List<ForumReply> replyList=forumReplyDao.getListByCondition(bean);
		List<ReplyBean> beanList=new ArrayList<ReplyBean>();
		if(replyList!=null&&replyList.size()>0){
			for (ForumReply forumReply : replyList) {
				ReplyBean newBean= new ReplyBean(forumReply);
				beanList.add(newBean);
			}
		}
		return beanList;
	}

	@Override
	public Long getSizeByCondition(ReplyBean bean) {
		return forumReplyDao.getSizeByCondition(bean);
	}

	@Override
	public String addReply(ReplyBean bean) {
		ForumReply reply=bean.beanToPojo();
		int row=forumReplyDao.insert(reply);
		if(row>0){
			return Boolean.TRUE.toString();
		}
		else{
			return Boolean.FALSE.toString();
		}
	}

}
