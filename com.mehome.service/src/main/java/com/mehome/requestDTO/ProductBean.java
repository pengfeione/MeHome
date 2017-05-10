package com.mehome.requestDTO;

import java.util.List;

import com.mehome.domain.ProductList;
import com.mehome.utils.PageMysqlUtil;

/**
 * 产品
 * @author xuyixun21
 *
 */
public class ProductBean extends PageMysqlUtil {
	

	private Integer productId;
	
	private String productName;
	
	private Integer supplierId;
	
	private Integer areaId;
	
	private Boolean productActive;
	
	private Integer productSort;
	/**
	 * 是否有个人福利
	 */
	private Boolean hasPersonal;
	
	private String personalWelfare;
	/**
	 * 是否有企业福利
	 */
	private Boolean isWelfare;
	
	private String productDetail;
	
	private String listpic;
	
	private String detailpic;
	
	private String address;
	
	private String position;
	
	private String rules;
	
	private String netRulesUrl;
	/**
	 * 房源数量
	 */
	private Integer houseResourceNum;
	/**
	 * 房源
	 */
	private List<HouseBean> houseList;
	/**
	 * 基础设施  包括公共交通  配套设施
	 */
	private List<BasicBean> basicList;
	/**
	 * 企业福利ID集合
	 */
	private List<Integer> welfareList;
	/**
	 * 点评集合
	 */
	private List<CommentBean> commentList;
	
	private Double score;
	
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public Integer getAreaId() {
		return areaId;
	}
	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}
	public Boolean getProductActive() {
		return productActive;
	}
	public void setProductActive(Boolean productActive) {
		this.productActive = productActive;
	}
	public Integer getProductSort() {
		return productSort;
	}
	public void setProductSort(Integer productSort) {
		this.productSort = productSort;
	}
	public Boolean getHasPersonal() {
		return hasPersonal;
	}
	public void setHasPersonal(Boolean hasPersonal) {
		this.hasPersonal = hasPersonal;
	}
	public String getPersonalWelfare() {
		return personalWelfare;
	}
	public void setPersonalWelfare(String personalWelfare) {
		this.personalWelfare = personalWelfare;
	}
	public Boolean getIsWelfare() {
		return isWelfare;
	}
	public void setIsWelfare(Boolean isWelfare) {
		this.isWelfare = isWelfare;
	}
	public String getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}
	public String getListpic() {
		return listpic;
	}
	public void setListpic(String listpic) {
		this.listpic = listpic;
	}
	public String getDetailpic() {
		return detailpic;
	}
	public void setDetailpic(String detailpic) {
		this.detailpic = detailpic;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getRules() {
		return rules;
	}
	public void setRules(String rules) {
		this.rules = rules;
	}
	public String getNetRulesUrl() {
		return netRulesUrl;
	}
	public void setNetRulesUrl(String netRulesUrl) {
		this.netRulesUrl = netRulesUrl;
	}
	public Integer getHouseResourceNum() {
		return houseResourceNum;
	}
	public void setHouseResourceNum(Integer houseResourceNum) {
		this.houseResourceNum = houseResourceNum;
	}
	public List<HouseBean> getHouseList() {
		return houseList;
	}
	public void setHouseList(List<HouseBean> houseList) {
		this.houseList = houseList;
	}
	public List<BasicBean> getBasicList() {
		return basicList;
	}
	public void setBasicList(List<BasicBean> basicList) {
		this.basicList = basicList;
	}
	public List<Integer> getWelfareList() {
		return welfareList;
	}
	public void setWelfareList(List<Integer> welfareList) {
		this.welfareList = welfareList;
	}
	public List<CommentBean> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentBean> commentList) {
		this.commentList = commentList;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public ProductList beanToPojo(){
		ProductList product = new ProductList();
		product.setAddress(this.getAddress());
		product.setAreaId(this.getAreaId());
		product.setDetailpic(this.getDetailpic());
		product.setHasPersonal(this.getHasPersonal()==null?Boolean.TRUE:this.getHasPersonal());
		if(this.getWelfareList()!=null&&this.getWelfareList().size()>0){
			product.setIsWelfare(Boolean.TRUE);
		}else{
			product.setIsWelfare(Boolean.FALSE);
		}
		product.setListpic(this.getListpic());
		product.setNetRulesUrl(this.getNetRulesUrl());
		product.setPersonalWelfare(this.getPersonalWelfare());
		product.setPosition(this.getPosition());
		product.setProductActive(Boolean.TRUE);
		product.setProductDetail(this.getProductDetail());
		product.setProductName(this.getProductName());
		product.setProductSort(this.getProductSort()==null?0:this.getProductSort());
		product.setRules(this.getRules());
		product.setSupplierId(this.getSupplierId());
		return product;
	}
	public ProductBean(ProductList product,List<HouseBean> houseList,List<BasicBean> basicList,List<CommentBean> commentList,List<Integer> welfareList){
		this.setAddress(product.getAddress());
		this.setAreaId(product.getAreaId());
		this.setBasicList(basicList);
		this.setCommentList(commentList);
		this.setDetailpic(product.getDetailpic());
		this.setHasPersonal(product.getHasPersonal());
		this.setHouseList(houseList);
		this.setIsWelfare(product.getIsWelfare());
		this.setListpic(product.getListpic());
		this.setNetRulesUrl(product.getNetRulesUrl());
		this.setPersonalWelfare(product.getPersonalWelfare());
		this.setPosition(product.getPosition());
		this.setProductActive(product.getProductActive());
		this.setProductDetail(product.getProductDetail());
		this.setProductName(product.getProductName());
		this.setProductSort(product.getProductSort());
		this.setRules(product.getRules());
		this.setSupplierId(product.getSupplierId());
		this.setWelfareList(welfareList);
	}
}
