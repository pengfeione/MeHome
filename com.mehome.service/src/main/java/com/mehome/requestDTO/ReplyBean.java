package com.mehome.requestDTO;

import java.util.Date;

import com.mehome.domain.ForumReply;
import com.mehome.utils.DateUtils;
import com.mehome.utils.PageMysqlUtil;

public class ReplyBean extends PageMysqlUtil{
    private String replyId;

    private String tid;

    private String type;

    private String replier;

    private Boolean isBest;

    private Integer floor;

    private Boolean isAdmin;

    private Short platform;

    private Float score;

    private String createTime;

    private Boolean isActive;

    private String listpic;

    private String content;

    private String formatContent;

	public String getReplyId() {
		return replyId;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
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

	public Short getPlatform() {
		return platform;
	}

	public void setPlatform(Short platform) {
		this.platform = platform;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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
    
    public ReplyBean(ForumReply reply){
    	this.setContent(reply.getContent());
    	this.setCreateTime(DateUtils.dateToDateStr(reply.getCreateTime()));
    	this.setFloor(reply.getFloor());
    	this.setFormatContent(reply.getFormatContent());
    	this.setIsActive(reply.getIsActive());
    	this.setIsAdmin(reply.getIsAdmin());
    	this.setIsBest(reply.getIsBest());
    	this.setListpic(reply.getListpic());
    	this.setPlatform(reply.getPlatform());
    	this.setReplier(reply.getReplier());
    	this.setReplyId(reply.getId());
    	this.setScore(reply.getScore());
    	this.setTid(reply.getTid());
    	this.setType(reply.getType());
    }
    public ForumReply beanToPojo(){
    	Date date = new Date();
    	ForumReply reply = new ForumReply();
    	reply.setContent(this.getContent());
    	reply.setCreateTime(date);
    	reply.setFloor(this.getFloor());
    	reply.setFormatContent(this.getFormatContent());
    	reply.setIsActive(this.getIsActive());
    	reply.setIsAdmin(this.getIsAdmin());
    	reply.setIsBest(this.getIsBest());
    	reply.setListpic(this.getListpic());
    	reply.setPlatform(this.getPlatform());
    	reply.setReplier(this.getReplier());
    	reply.setScore(this.getScore());
    	reply.setTid(this.getTid());
    	reply.setType(this.getType());
    	return reply;
    }
}
