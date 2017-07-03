package com.mehome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mehome.dao.ForumReplyDao;
import com.mehome.domain.ForumReply;
import com.mehome.requestDTO.ReplyBean;
import com.mehome.service.iface.IReplyService;
@Service("IReplyService")
public class ReplyServiceImpl implements IReplyService {

	private Logger log = Logger.getLogger(this.getClass());
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
	public synchronized String addReply(ReplyBean bean) {
		String tid=bean.getTid();
		if(StringUtils.isBlank(tid)){
			log.error("资讯Id(tid)未传");
			return "";
		}
		Integer max=forumReplyDao.getMaxFloorByTid(tid);
		if(max==null){
			max=0;
		}
		bean.setFloor(max+1);
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
