package com.mehome.requestDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.mehome.domain.ForumTopic;
import com.mehome.enumDTO.TopicTypeEnum;
import com.mehome.utils.DateUtils;
import com.mehome.utils.OrderIdUtils;
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

    private String lastReplyTime;

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

    private List<String> listpic;

    private String content;

    private String formatContent;

    private String topicTypeDesc;
    
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

	public String getLastReplyTime() {
		return lastReplyTime;
	}

	public void setLastReplyTime(String lastReplyTime) {
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

	public List<String> getListpic() {
		return listpic;
	}

	public void setListpic(List<String> listpic) {
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
    
    public String getTopicTypeDesc() {
		return topicTypeDesc;
	}

	public void setTopicTypeDesc(String topicTypeDesc) {
		this.topicTypeDesc = topicTypeDesc;
	}

	public TopicBean(){
		
	}
	public TopicBean(ForumTopic topic){
		TopicTypeEnum[] enums=TopicTypeEnum.values();
		for (TopicTypeEnum topicTypeEnum : enums) {
			if(topic.getTopicType().equals(topicTypeEnum.getKey())){
				this.setTopicTypeDesc(topicTypeEnum.getValue());
			}
		}
		this.setBid(topic.getBid());
		this.setContent(topic.getContent());
		this.setContentFrom(topic.getContentFrom());
		this.setCreateTime(DateUtils.dateToStr(topic.getCreateTime()));
		this.setDisplayLocation(topic.getDisplayLocation());
		this.setDisplayOrder(topic.getDisplayOrder());
		this.setDisplayPic(topic.getDisplayPic());
		this.setFid(topic.getFid());
		this.setFormatContent(topic.getFormatContent());
		this.setIsActive(topic.getIsActive());
		this.setLastReplier(topic.getLastReplier());
		if(topic.getLastReplyTime()!=null)
		this.setLastReplyTime(DateUtils.dateToStr(topic.getLastReplyTime()));
		if (!StringUtils.isEmpty(topic.getListpic()) && topic.getListpic().length() > 2) {
            List<String> detailPicList = new ArrayList<String>();
            String detailPic = topic.getListpic().substring(1, topic.getListpic().length() - 1);
            String[] pics = detailPic.split(",");
            for (String string : pics) {
                detailPicList.add(string);
            }
            this.setListpic(detailPicList);
        }
		this.setPlatform(topic.getPlatform());
		this.setSubject(topic.getSubject());
		this.setSummary(topic.getSummary());
		this.setTid(topic.getTid());
		this.setTopicType(topic.getTopicType());
		this.setTopicUrl(topic.getTopicUrl());
		this.setUid(topic.getUid());
    }
    public ForumTopic beanToPojo(){
    	Date date=new Date();
    	ForumTopic topic=new ForumTopic();
    	topic.setBid(this.getBid()==null?1:this.getBid());
    	topic.setContent(this.getContent());
    	topic.setContentFrom(StringUtils.isBlank(this.getContentFrom())?"Me+":this.getContentFrom());
    	topic.setCreateTime(date);
    	topic.setDisplayLocation(this.getDisplayLocation());
    	topic.setDisplayOrder(this.getDisplayOrder());
    	topic.setDisplayPic(this.getDisplayPic());
    	topic.setFid(this.getFid()==null?"1":this.getFid());
    	topic.setFormatContent(this.getFormatContent());
    	topic.setIsActive(Boolean.TRUE);
    	topic.setLastReplier(this.getLastReplier());
//    	topic.setLastReplyTime(date);
    	topic.setListpic(this.getListpic()==null?null:this.getListpic().toString());
    	topic.setPlatform(this.getPlatform()==null?(short) 2:this.getPlatform());
    	topic.setReplies(0);
    	topic.setSubject(StringUtils.isBlank(this.getSubject())?"未定义标题":this.getSubject());
    	topic.setSummary(this.getSummary());
    	topic.setTid(StringUtils.isBlank(this.getTid())?OrderIdUtils.getUUID():this.getTid());
    	topic.setUid(StringUtils.isBlank(this.getUid())?"未定义编辑者":this.getUid());
    	topic.setTopicType(StringUtils.isBlank(this.getTopicType())?"undefined":this.getTopicType());
    	topic.setUpdateTime(date);
    	topic.setViews(0);
    	topic.setWatchs(0);
		return topic;
    }
    public ForumTopic compareToPojo(ForumTopic topic,TopicBean bean){
    	if(StringUtils.isNotBlank(bean.getContent())){
    		topic.setContent(bean.getContent());
    	}
    	if(StringUtils.isNotBlank(bean.getContentFrom())){
    		topic.setContentFrom(bean.getContentFrom());
    	}
    	if(StringUtils.isNotBlank(bean.getDisplayLocation())){
    		topic.setDisplayLocation(bean.getDisplayLocation());
    	}
    	if(StringUtils.isNotBlank(bean.getDisplayPic())){
    		topic.setDisplayPic(bean.getDisplayPic());
    	}
    	if(StringUtils.isNotBlank(bean.getFid())){
    		topic.setFid(bean.getFid());
    	}
    	if(StringUtils.isNotBlank(bean.getFormatContent())){
    		topic.setFormatContent(bean.getFormatContent());
    	}
    	if(StringUtils.isNotBlank(bean.getLastReplier())){
    		topic.setLastReplier(bean.getLastReplier());
    	}
    	if(bean.getListpic()!=null){
    		topic.setListpic(bean.getListpic().toString());
    	}
    	if(StringUtils.isNotBlank(bean.getSubject())){
    		topic.setSubject(bean.getSubject());
    	}
    	if(StringUtils.isNotBlank(bean.getSummary())){
    		topic.setSummary(bean.getSummary());
    	}
    	if(StringUtils.isNotBlank(bean.getTid())){
    		topic.setTid(bean.getTid());
    	}
    	if(StringUtils.isNotBlank(bean.getTopicType())){
    		topic.setTopicType(bean.getTopicType());
    	}
    	if(StringUtils.isNoneBlank(bean.getTopicUrl())){
    		topic.setTopicUrl(bean.getTopicUrl());
    	}
    	if(bean.getIsActive()!=null){
    		topic.setIsActive(bean.getIsActive());
    	}
    	return topic;
    }
}
