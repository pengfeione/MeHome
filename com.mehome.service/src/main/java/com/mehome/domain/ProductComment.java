package com.mehome.domain;

import java.util.Date;

public class ProductComment {
    private String commentId;

    private Integer productId;

    private String type;

    private String replier;

    private Boolean isBest;

    private Integer floor;

    private Boolean isAdmin;

    private String fromTag;

    private Float score;

    private Date createTime;

    private Boolean isActive;

    private String listpic;

    private String content;

    private String formatContent;
    
    private Float comfort;
    
    private Float convenient;
    
    private Float service;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId == null ? null : commentId.trim();
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
        this.type = type == null ? null : type.trim();
    }

    public String getReplier() {
        return replier;
    }

    public void setReplier(String replier) {
        this.replier = replier == null ? null : replier.trim();
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
        this.fromTag = fromTag == null ? null : fromTag.trim();
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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
        ProductComment other = (ProductComment) that;
        return (this.getCommentId() == null ? other.getCommentId() == null : this.getCommentId().equals(other.getCommentId()))
            && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getReplier() == null ? other.getReplier() == null : this.getReplier().equals(other.getReplier()))
            && (this.getIsBest() == null ? other.getIsBest() == null : this.getIsBest().equals(other.getIsBest()))
            && (this.getFloor() == null ? other.getFloor() == null : this.getFloor().equals(other.getFloor()))
            && (this.getIsAdmin() == null ? other.getIsAdmin() == null : this.getIsAdmin().equals(other.getIsAdmin()))
            && (this.getFromTag() == null ? other.getFromTag() == null : this.getFromTag().equals(other.getFromTag()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getIsActive() == null ? other.getIsActive() == null : this.getIsActive().equals(other.getIsActive()))
            && (this.getListpic() == null ? other.getListpic() == null : this.getListpic().equals(other.getListpic()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getFormatContent() == null ? other.getFormatContent() == null : this.getFormatContent().equals(other.getFormatContent()))
            && (this.getComfort() == null ? other.getComfort() == null : this.getComfort().equals(other.getComfort()))
        	&& (this.getConvenient() == null ? other.getConvenient() == null : this.getConvenient().equals(other.getConvenient()))
        	&& (this.getService() == null ? other.getService() == null : this.getService().equals(other.getService()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCommentId() == null) ? 0 : getCommentId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getReplier() == null) ? 0 : getReplier().hashCode());
        result = prime * result + ((getIsBest() == null) ? 0 : getIsBest().hashCode());
        result = prime * result + ((getFloor() == null) ? 0 : getFloor().hashCode());
        result = prime * result + ((getIsAdmin() == null) ? 0 : getIsAdmin().hashCode());
        result = prime * result + ((getFromTag() == null) ? 0 : getFromTag().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getIsActive() == null) ? 0 : getIsActive().hashCode());
        result = prime * result + ((getListpic() == null) ? 0 : getListpic().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getFormatContent() == null) ? 0 : getFormatContent().hashCode());
        result = prime * result + ((getComfort() == null) ? 0 : getComfort().hashCode());
        result = prime * result + ((getConvenient() == null) ? 0 : getConvenient().hashCode());
        result = prime * result + ((getService() == null) ? 0 : getService().hashCode());
        return result;
    }
}