package com.mehome.service.iface;

import java.util.List;

import com.mehome.requestDTO.ReplyBean;

public interface IReplyService {
	List<ReplyBean> getListByCondition(ReplyBean bean);
	
	Long getSizeByCondition(ReplyBean bean);
	
	String addReply(ReplyBean bean);
}
