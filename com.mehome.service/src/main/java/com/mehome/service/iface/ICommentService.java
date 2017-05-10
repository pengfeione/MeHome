package com.mehome.service.iface;

import java.util.List;

import com.mehome.requestDTO.CommentBean;

public interface ICommentService {
	public List<CommentBean> getListByCondition(CommentBean bean);

	public String addComment(CommentBean bean);

	public Long getSizeByCondition(CommentBean bean);

	public String updateComment(CommentBean bean);
}
