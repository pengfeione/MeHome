package com.mehome.requestDTO;

import java.util.Date;
import java.util.UUID;

import org.springframework.util.StringUtils;

import com.mehome.domain.ProductComment;
import com.mehome.enumDTO.ScoreEnum;
import com.mehome.utils.OrderIdUtils;
import com.mehome.utils.PageMysqlUtil;

public class CommentBean extends PageMysqlUtil{

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
	
	private Boolean isActive;
	
	private Float comfort;
    
    private Float convenient;
    
    private Float service;
    
    private String comfortDesc;
    
    private String convenientDesc;
    
    private String serviceDesc;

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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Float getComfort() {
		return comfort;
	}

	public void setComfort(Float comfort) {
		this.comfort = comfort;
	}

	public Float getConvenient() {
		return convenient;
	}

	public void setConvenient(Float convenient) {
		this.convenient = convenient;
	}

	public Float getService() {
		return service;
	}

	public void setService(Float service) {
		this.service = service;
	}

	public String getComfortDesc() {
		return comfortDesc;
	}

	public void setComfortDesc(String comfortDesc) {
		this.comfortDesc = comfortDesc;
	}

	public String getConvenientDesc() {
		return convenientDesc;
	}

	public void setConvenientDesc(String convenientDesc) {
		this.convenientDesc = convenientDesc;
	}

	public String getServiceDesc() {
		return serviceDesc;
	}

	public void setServiceDesc(String serviceDesc) {
		this.serviceDesc = serviceDesc;
	}

	public ProductComment beanToPojo(Boolean addBoolean){
		ProductComment comment = new ProductComment();
		comment.setCommentId(addBoolean?OrderIdUtils.getUUID():this.getCommentId());
		comment.setContent(this.getContent());
		comment.setCreateTime(new Date());
		comment.setFloor(this.getFloor()==null&&addBoolean?0:this.getFloor());
		comment.setFormatContent(this.getFormatContent());
		comment.setFromTag(StringUtils.isEmpty(this.getFromTag())&&addBoolean?"meApp_1.0":this.getFromTag());
		comment.setIsActive(addBoolean?Boolean.TRUE:this.getIsActive());
		comment.setIsAdmin(this.getIsAdmin()==null&&addBoolean?Boolean.FALSE:this.getIsAdmin());
		comment.setIsBest(this.getIsBest()==null&&addBoolean?Boolean.FALSE:this.getIsBest());
		comment.setListpic(this.getListpic());
		comment.setProductId(this.getProductId());
		comment.setReplier(this.getReplier());
		comment.setScore(this.getScore()==null&&addBoolean?5.0f:this.getScore());
		comment.setType(StringUtils.isEmpty(this.getType())&&addBoolean?"undefined":this.getType());
		comment.setComfort(this.getComfort()==null&&addBoolean?5.0f:this.getComfort());
		comment.setConvenient(this.getConvenient()==null&&addBoolean?5.0f:this.getConvenient());
		comment.setService(this.getService()==null&&addBoolean?5.0f:this.getService());
		return comment;
	}

	public CommentBean(){

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
		this.setComfort(comment.getComfort());
		this.setConvenient(comment.getConvenient());
		this.setService(comment.getService());
		ScoreEnum[] enums=ScoreEnum.values();
		for (ScoreEnum scoreEnum : enums) {
			if(comment.getComfort().intValue()==scoreEnum.getKey()){
				this.setComfortDesc(scoreEnum.getValue());
			}
			if(comment.getConvenient().intValue()==scoreEnum.getKey()){
				this.setConvenientDesc(scoreEnum.getValue());
			}
			if(comment.getService().intValue()==scoreEnum.getKey()){
				this.setServiceDesc(scoreEnum.getValue());
			}
		}
	}
	
}
