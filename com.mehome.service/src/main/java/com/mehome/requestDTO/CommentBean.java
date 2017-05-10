package com.mehome.requestDTO;

import java.util.Date;
import java.util.UUID;

import org.springframework.util.StringUtils;

import com.mehome.domain.ProductComment;
import com.mehome.utils.OrderIdUtils;

public class CommentBean {

	private String commentId;
	
	private Integer productId;
	
	private String type;
	
	private String replier;
	
	private String listpic;
	
	private String content;
	
	private String formatContent;
	
	private Boolean isBest;
	
	private Integer floor;
	
	private Boolean isAdmin;
	
	private String fromTag;
	
	private Float score;

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getReplier() {
		return replier;
	}

	public void setReplier(String replier) {
		this.replier = replier;
	}

	public String getListpic() {
		return listpic;
	}

	public void setListpic(String listpic) {
		this.listpic = listpic;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFormatContent() {
		return formatContent;
	}

	public void setFormatContent(String formatContent) {
		this.formatContent = formatContent;
	}

	public Boolean getIsBest() {
		return isBest;
	}

	public void setIsBest(Boolean isBest) {
		this.isBest = isBest;
	}

	public Integer getFloor() {
		return floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getFromTag() {
		return fromTag;
	}

	public void setFromTag(String fromTag) {
		this.fromTag = fromTag;
	}
	
	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public ProductComment beanToPojo(){
		ProductComment comment = new ProductComment();
		comment.setCommentId(OrderIdUtils.getUUID());
		comment.setContent(this.getContent());
		comment.setCreateTime(new Date());
		comment.setFloor(this.getFloor());
		comment.setFormatContent(this.getFormatContent());
		comment.setFromTag(StringUtils.isEmpty(this.getFromTag())?"meApp_1.0":this.getFromTag());
		comment.setIsActive(Boolean.TRUE);
		comment.setIsAdmin(this.getIsAdmin()==null?Boolean.FALSE:this.getIsAdmin());
		comment.setIsBest(this.getIsBest()==null?Boolean.FALSE:this.getIsBest());
		comment.setListpic(this.getListpic());
		comment.setProductId(this.getProductId());
		comment.setReplier(this.getReplier());
		comment.setScore(this.getScore()==null?5.0f:this.getScore());
		comment.setType(StringUtils.isEmpty(this.getType())?"undefined":this.getType());
		return comment;
	}
	
	public CommentBean(ProductComment comment){
		this.setCommentId(comment.getCommentId());
		this.setContent(comment.getContent());
		this.setFloor(comment.getFloor());
		this.setFormatContent(comment.getFormatContent());
		this.setFromTag(comment.getFromTag());
		this.setIsAdmin(comment.getIsAdmin());
		this.setIsBest(comment.getIsBest());
		this.setListpic(comment.getListpic());
		this.setProductId(comment.getProductId());
		this.setReplier(comment.getReplier());
		this.setScore(comment.getScore());
		this.setType(comment.getType());
	}
	
}
