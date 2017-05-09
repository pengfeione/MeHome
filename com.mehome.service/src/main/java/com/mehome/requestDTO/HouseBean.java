package com.mehome.requestDTO;

import com.mehome.domain.HouseResource;
import com.mehome.enumDTO.HouseStatusEnum;
import com.mehome.utils.DateUtils;
import com.mehome.utils.PageMysqlUtil;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 房源
 * @author xuyixun21
 *
 */
public class HouseBean extends PageMysqlUtil {

	private Integer houseId;
	
	private Integer areaId;
	
	private Integer supplierId;
	
	private String subject;
	
	private String summary;
	
	private String detail;
	
	private String address;
	
	private String listpic;
	
	private List<String> detailpic;
	
	private String position;
	
	private Integer sort;
	
	private Integer roomType;
	
	private String roomTypeDesc;
	
	private Double roomArea;
	
	private Integer roomRent;
	
	private String payType;
	
	private Integer status;
	
	private String startTime;
	
	private String endTime;
	
	private String leaseHolder;

	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
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

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getListpic() {
		return listpic;
	}

	public void setListpic(String listpic) {
		this.listpic = listpic;
	}

	public List<String> getDetailpic() {
		return detailpic;
	}

	public void setDetailpic(List<String> detailpic) {
		this.detailpic = detailpic;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getRoomType() {
		return roomType;
	}

	public void setRoomType(Integer roomType) {
		this.roomType = roomType;
	}

	public String getRoomTypeDesc() {
		return roomTypeDesc;
	}

	public void setRoomTypeDesc(String roomTypeDesc) {
		this.roomTypeDesc = roomTypeDesc;
	}

	public Double getRoomArea() {
		return roomArea;
	}

	public void setRoomArea(Double roomArea) {
		this.roomArea = roomArea;
	}

	public Integer getRoomRent() {
		return roomRent;
	}

	public void setRoomRent(Integer roomRent) {
		this.roomRent = roomRent;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLeaseHolder() {
		return leaseHolder;
	}

	public void setLeaseHolder(String leaseHolder) {
		this.leaseHolder = leaseHolder;
	}
	
	public HouseResource beanToPojo(){
		HouseResource resource = new HouseResource();
		resource.setAddress(this.getAddress());
		resource.setAreaId(this.getAreaId());
		resource.setCreateTime(new Date());
		resource.setDetail(this.getDetail());
		resource.setDetailpic(this.getDetailpic()==null?null:this.getDetailpic().toString());
		resource.setEndTime(StringUtils.isEmpty(this.getEndTime())?null:DateUtils.strToDate(this.getEndTime()));
		resource.setLeaseHolder(this.getLeaseHolder());
		resource.setListpic(this.getListpic());
		resource.setPayType(this.getPayType());
		resource.setPosition(this.getPosition());
		resource.setRoomArea(this.getRoomArea());
		resource.setRoomRent(this.getRoomRent());
		resource.setRoomType(this.getRoomType());
		resource.setRoomTypeDesc(this.getRoomTypeDesc());
		resource.setSort(this.getSort()==null?0:this.getSort());
		resource.setStartTime(StringUtils.isEmpty(this.getStartTime())?null:DateUtils.strToDate(this.getStartTime()));
		resource.setStatus(this.getStatus()==null?HouseStatusEnum.AVAILABLE.getKey():this.getStatus());
		resource.setSubject(this.getSubject());
		resource.setSummary(this.getSummary());
		resource.setSupplierId(this.getSupplierId());
		resource.setUpdateTime(new Date());
		return resource;
	}
	
	public HouseBean(HouseResource resource){
		this.setAddress(resource.getAddress());
		this.setAreaId(resource.getAreaId());
		this.setDetail(resource.getDetail());
		this.setDetailpic(StringUtils.isEmpty(resource.getDetailpic())?null:Arrays.asList(resource.getDetailpic()));
		this.setEndTime(resource.getEndTime()==null?null:DateUtils.dateToStr(resource.getEndTime()));
		this.setHouseId(resource.getHouseId());
		this.setLeaseHolder(resource.getLeaseHolder());
		this.setListpic(resource.getListpic());
		this.setPayType(resource.getPayType());
		this.setPosition(resource.getPosition());
		this.setRoomArea(resource.getRoomArea());
		this.setRoomRent(resource.getRoomRent());
		this.setRoomType(resource.getRoomType());
		this.setRoomTypeDesc(resource.getRoomTypeDesc());
		this.setSort(resource.getSort());
		this.setStartTime(resource.getStartTime()==null?null:DateUtils.dateToStr(resource.getStartTime()));
		this.setStatus(resource.getStatus());
		this.setSubject(resource.getSubject());
		this.setSummary(resource.getSummary());
		this.setSupplierId(resource.getSupplierId());
	}
	
	
}
