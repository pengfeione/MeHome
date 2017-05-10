package com.mehome.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mehome.requestDTO.CommentBean;
import com.mehome.service.iface.ICommentService;
@Service("ICommentService")
public class CommentServiceImpl implements ICommentService {

	@Override
	public List<CommentBean> getListByCondition(CommentBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addComment(CommentBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getSizeByCondition(CommentBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateComment(CommentBean bean) {
		// TODO Auto-generated method stub
		return null;
	}

}
