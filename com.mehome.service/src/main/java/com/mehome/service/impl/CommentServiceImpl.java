package com.mehome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mehome.dao.HouseResourceDao;
import com.mehome.dao.ProductCommentDao;
import com.mehome.domain.HouseResource;
import com.mehome.domain.ProductComment;
import com.mehome.requestDTO.CommentBean;
import com.mehome.requestDTO.HouseBean;
import com.mehome.service.iface.ICommentService;
@Service("ICommentService")
public class CommentServiceImpl implements ICommentService {

	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private ProductCommentDao ProductCommentDAO;
	@Override
	public List<CommentBean> getListByCondition(CommentBean bean) {
		List<ProductComment> commentList=ProductCommentDAO.getListByCondition(bean);
		List<CommentBean> commentBeanList=new ArrayList<CommentBean>();
		if(commentList!=null&&commentList.size()>0){
			for (ProductComment comment : commentList) {
				CommentBean newBean=new CommentBean(comment);
				commentBeanList.add(newBean);
			}
		}
		return commentBeanList;
	}

	@Override
	public synchronized String addComment(CommentBean bean) {
		ProductComment comment = null;
		try {
			Integer productId=bean.getProductId();
			if(productId==null){
				log.error("产品Id未传");
				return "";
			}
			Integer max=ProductCommentDAO.getMaxFloorByProduct(productId);
			if(max==null)
				max=0;
			bean.setFloor(max+1);
			comment = bean.beanToPojo(Boolean.TRUE);
			ProductCommentDAO.insert(comment);
		} catch (Exception e) {
			log.error("加入点评出错:" + e);
		}
		return comment.getCommentId() == null ? "" : comment.getCommentId().toString();
	}

	@Override
	public Long getSizeByCondition(CommentBean bean) {
		Long size=ProductCommentDAO.getSizeByCondition(bean);
		return size;
	}

	@Override
	public String updateComment(CommentBean bean) {
		ProductComment comment = null;
		try {
			comment = bean.compareBean();
			int row = ProductCommentDAO.updateRequired(comment);
		} catch (Exception e) {
			log.error("更新点评出错:" + e);
			return Boolean.FALSE.toString();
		}
		return Boolean.TRUE.toString();
	}

}
