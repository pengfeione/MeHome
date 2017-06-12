package com.mehome.requestDTO;

import java.util.Date;

import com.mehome.domain.ForumTopic;
import com.mehome.utils.PageMysqlUtil;

public class TopicBean extends PageMysqlUtil{
	private String tid;

    private String fid;

    private String uid;

    private Integer bid;

    private String topicType;

    private String subject;

    private String summary;

    private String contentFrom;

    private Short platform;

    private Date lastReplyTime;

    private String lastReplier;

    private Integer views;

    private Integer replies;

    private Integer watchs;

    private Short displayOrder;

    private String createTime;

    private String updateTime;

    private Boolean isActive;

    private String displayLocation;

    private String displayPic;

    private String topicUrl;

    private String listpic;

    private String content;

    private String formatContent;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getTopicType() {
		return topicType;
	}

	public void setTopicType(String topicType) {
		this.topicType = topicType;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContentFrom() {
		return contentFrom;
	}

	public void setContentFrom(String contentFrom) {
		this.contentFrom = contentFrom;
	}

	public Short getPlatform() {
		return platform;
	}

	public void setPlatform(Short platform) {
		this.platform = platform;
	}

	public Date getLastReplyTime() {
		return lastReplyTime;
	}

	public void setLastReplyTime(Date lastReplyTime) {
		this.lastReplyTime = lastReplyTime;
	}

	public String getLastReplier() {
		return lastReplier;
	}

	public void setLastReplier(String lastReplier) {
		this.lastReplier = lastReplier;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}

	public Integer getReplies() {
		return replies;
	}

	public void setReplies(Integer replies) {
		this.replies = replies;
	}

	public Integer getWatchs() {
		return watchs;
	}

	public void setWatchs(Integer watchs) {
		this.watchs = watchs;
	}

	public Short getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Short displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getDisplayLocation() {
		return displayLocation;
	}

	public void setDisplayLocation(String displayLocation) {
		this.displayLocation = displayLocation;
	}

	public String getDisplayPic() {
		return displayPic;
	}

	public void setDisplayPic(String displayPic) {
		this.displayPic = displayPic;
	}

	public String getTopicUrl() {
		return topicUrl;
	}

	public void setTopicUrl(String topicUrl) {
		this.topicUrl = topicUrl;
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
    
    public TopicBean(ForumTopic topic){
    	
    }
    public ForumTopic beanToPojo(){
    	ForumTopic topic=new ForumTopic();
		return topic;
    }
    public ForumTopic compareToPojo(){
    	ForumTopic topic=new ForumTopic();
    	return topic;
    }
}
