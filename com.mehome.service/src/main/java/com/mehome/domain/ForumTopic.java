package com.mehome.domain;

import java.util.Date;

public class ForumTopic {
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

    private Date createTime;

    private Date updateTime;

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
        this.tid = tid == null ? null : tid.trim();
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid == null ? null : fid.trim();
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid == null ? null : uid.trim();
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
        this.topicType = topicType == null ? null : topicType.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getContentFrom() {
        return contentFrom;
    }

    public void setContentFrom(String contentFrom) {
        this.contentFrom = contentFrom == null ? null : contentFrom.trim();
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
        this.lastReplier = lastReplier == null ? null : lastReplier.trim();
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
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
        this.displayLocation = displayLocation == null ? null : displayLocation.trim();
    }

    public String getDisplayPic() {
        return displayPic;
    }

    public void setDisplayPic(String displayPic) {
        this.displayPic = displayPic == null ? null : displayPic.trim();
    }

    public String getTopicUrl() {
        return topicUrl;
    }

    public void setTopicUrl(String topicUrl) {
        this.topicUrl = topicUrl == null ? null : topicUrl.trim();
    }

    public String getListpic() {
        return listpic;
    }

    public void setListpic(String listpic) {
        this.listpic = listpic == null ? null : listpic.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getFormatContent() {
        return formatContent;
    }

    public void setFormatContent(String formatContent) {
        this.formatContent = formatContent == null ? null : formatContent.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ForumTopic other = (ForumTopic) that;
        return (this.getTid() == null ? other.getTid() == null : this.getTid().equals(other.getTid()))
            && (this.getFid() == null ? other.getFid() == null : this.getFid().equals(other.getFid()))
            && (this.getUid() == null ? other.getUid() == null : this.getUid().equals(other.getUid()))
            && (this.getBid() == null ? other.getBid() == null : this.getBid().equals(other.getBid()))
            && (this.getTopicType() == null ? other.getTopicType() == null : this.getTopicType().equals(other.getTopicType()))
            && (this.getSubject() == null ? other.getSubject() == null : this.getSubject().equals(other.getSubject()))
            && (this.getSummary() == null ? other.getSummary() == null : this.getSummary().equals(other.getSummary()))
            && (this.getContentFrom() == null ? other.getContentFrom() == null : this.getContentFrom().equals(other.getContentFrom()))
            && (this.getPlatform() == null ? other.getPlatform() == null : this.getPlatform().equals(other.getPlatform()))
            && (this.getLastReplyTime() == null ? other.getLastReplyTime() == null : this.getLastReplyTime().equals(other.getLastReplyTime()))
            && (this.getLastReplier() == null ? other.getLastReplier() == null : this.getLastReplier().equals(other.getLastReplier()))
            && (this.getViews() == null ? other.getViews() == null : this.getViews().equals(other.getViews()))
            && (this.getReplies() == null ? other.getReplies() == null : this.getReplies().equals(other.getReplies()))
            && (this.getWatchs() == null ? other.getWatchs() == null : this.getWatchs().equals(other.getWatchs()))
            && (this.getDisplayOrder() == null ? other.getDisplayOrder() == null : this.getDisplayOrder().equals(other.getDisplayOrder()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsActive() == null ? other.getIsActive() == null : this.getIsActive().equals(other.getIsActive()))
            && (this.getDisplayLocation() == null ? other.getDisplayLocation() == null : this.getDisplayLocation().equals(other.getDisplayLocation()))
            && (this.getDisplayPic() == null ? other.getDisplayPic() == null : this.getDisplayPic().equals(other.getDisplayPic()))
            && (this.getTopicUrl() == null ? other.getTopicUrl() == null : this.getTopicUrl().equals(other.getTopicUrl()))
            && (this.getListpic() == null ? other.getListpic() == null : this.getListpic().equals(other.getListpic()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getFormatContent() == null ? other.getFormatContent() == null : this.getFormatContent().equals(other.getFormatContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTid() == null) ? 0 : getTid().hashCode());
        result = prime * result + ((getFid() == null) ? 0 : getFid().hashCode());
        result = prime * result + ((getUid() == null) ? 0 : getUid().hashCode());
        result = prime * result + ((getBid() == null) ? 0 : getBid().hashCode());
        result = prime * result + ((getTopicType() == null) ? 0 : getTopicType().hashCode());
        result = prime * result + ((getSubject() == null) ? 0 : getSubject().hashCode());
        result = prime * result + ((getSummary() == null) ? 0 : getSummary().hashCode());
        result = prime * result + ((getContentFrom() == null) ? 0 : getContentFrom().hashCode());
        result = prime * result + ((getPlatform() == null) ? 0 : getPlatform().hashCode());
        result = prime * result + ((getLastReplyTime() == null) ? 0 : getLastReplyTime().hashCode());
        result = prime * result + ((getLastReplier() == null) ? 0 : getLastReplier().hashCode());
        result = prime * result + ((getViews() == null) ? 0 : getViews().hashCode());
        result = prime * result + ((getReplies() == null) ? 0 : getReplies().hashCode());
        result = prime * result + ((getWatchs() == null) ? 0 : getWatchs().hashCode());
        result = prime * result + ((getDisplayOrder() == null) ? 0 : getDisplayOrder().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsActive() == null) ? 0 : getIsActive().hashCode());
        result = prime * result + ((getDisplayLocation() == null) ? 0 : getDisplayLocation().hashCode());
        result = prime * result + ((getDisplayPic() == null) ? 0 : getDisplayPic().hashCode());
        result = prime * result + ((getTopicUrl() == null) ? 0 : getTopicUrl().hashCode());
        result = prime * result + ((getListpic() == null) ? 0 : getListpic().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getFormatContent() == null) ? 0 : getFormatContent().hashCode());
        return result;
    }
}