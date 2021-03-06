package com.mehome.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mehome.dao.*;
import com.mehome.domain.*;
import com.mehome.enumDTO.UserCompanyEnum;
import com.mehome.exceptions.InfoException;
import com.mehome.requestDTO.BasicBean;
import com.mehome.requestDTO.CompanyWelfareNotice;
import com.mehome.requestDTO.ProductBean;
import com.mehome.requestDTO.ProductCompanyWelfareDTO;
import com.mehome.resonpseDTO.ProductCompanyWelfare;
import com.mehome.service.iface.IProductService;
import com.mehome.utils.AssertUtils;
import com.mehome.utils.LbsAmapUtils;
import com.mehome.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Service("IProductService")
public class ProductServiceImpl implements IProductService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private ProductListDao productListDAO;
    @Autowired
    private SupplierListDao supplierListDao;
    @Autowired
    private ProductRalationBasicDao productRalationBasicDAO;
    @Autowired
    private BasicFacilitiesDao basicFacilitiesDAO;
    @Autowired
    private ProductRelationWelfareDao productRelationWelfareDao;
    @Autowired
    private CompanyListDao companyListDao;
    @Autowired
    private CompanyWelfareDao companyWelfareDao;
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private HouseResourceDao houseResourceDao;

    @Override
    public List<ProductBean> getListByCondition(ProductBean bean) {
        List<ProductList> productList = productListDAO.getListByCondition(bean);
        List<ProductBean> productBeanList = new ArrayList<ProductBean>();
        if (productList != null && productList.size() > 0) {
            for (ProductList product : productList) {
                Integer productId = product.getProductId();
                BasicBean basic = new BasicBean();
                basic.setProductId(productId);
                List<ProductRalationBasic> relationList = productRalationBasicDAO.getListByCondition(basic);
                List<BasicBean> basicBeanList = new ArrayList<BasicBean>();
                if (relationList != null && relationList.size() > 0) {
                    for (ProductRalationBasic productRalationBasic : relationList) {
                        Integer basicId = productRalationBasic.getBasicId();
                        BasicFacilities basicObj = basicFacilitiesDAO.selectById(basicId);
                        BasicBean newBean = new BasicBean(basicObj);
                        basicBeanList.add(newBean);
                    }
                }
                ProductBean newBean = new ProductBean(product, null, basicBeanList, null, null);
                if (null != newBean && null != newBean.getSupplierId()) {
                    SupplierList supplierList = supplierListDao.selectById(newBean.getSupplierId());
                    if (null != supplierList) {
                        newBean.setSupplierName(supplierList.getSupplierName());
                    } else {
                        newBean.setSupplierName(String.valueOf(newBean.getSupplierId()));
                    }
                }
                productBeanList.add(newBean);
            }
        }
        return productBeanList;
    }

    @Override
    public JSONObject getCompanyWelfare(CompanyWelfareDTO companyWelfareDTO) {
        //若用户ID不为空，需要判断用户和企业的关系
        if (null != companyWelfareDTO.getUserId()) {
            UserInfo userInfo = userInfoDao.selectById(companyWelfareDTO.getUserId());
            if (null != userInfo
                    && null != userInfo.getCompanyId()
                    && null != companyWelfareDTO.getCompanyId()
                    && userInfo.getCompanyStatus() == UserCompanyEnum.ACTIVE.getKey()
                    ) {
                //是关联该产品的所有企业福利id
                List<String> welfareIds = productRelationWelfareDao.selectByProductIdAndCompanyId(companyWelfareDTO.getProductId(), userInfo.getCompanyId());
                if (welfareIds.size() <= 0) {
                    throw new InfoException("暂无企业福利！");
                } else {
                    return JSONObject.parseObject(welfareIds.get(0));
                }
            } else {
                throw new InfoException("暂无企业福利！");
            }
        } else {
            throw new InfoException("暂无企业福利！");
        }
    }

    @Override
    public JSONObject getWelfare(CompanyWelfareDTO companyWelfareDTO) {
        JSONObject personWelfare = null;
        JSONObject companyWelfare = null;
        JSONObject houseWelfare = null;
        if (null != companyWelfareDTO.getProductId()) {
            ProductList productList = productListDAO.selectById(companyWelfareDTO.getProductId());
            if (null != productList && StringUtils.isNotNull(productList.getPersonalWelfare())) {
                personWelfare = JSONObject.parseObject(productList.getPersonalWelfare());
            }
        }
        if (null != companyWelfareDTO.getUserId()) {
            UserInfo userInfo = userInfoDao.selectById(companyWelfareDTO.getUserId());
            if (null != userInfo
                    && null != userInfo.getCompanyId()
                    && null != companyWelfareDTO.getCompanyId()
                    && userInfo.getCompanyStatus() == UserCompanyEnum.ACTIVE.getKey()
                    ) {
                //是关联该产品的所有企业福利id
                List<String> welfareIds = productRelationWelfareDao.selectByProductIdAndCompanyId(companyWelfareDTO.getProductId(), userInfo.getCompanyId());
                if (welfareIds.size() > 0) {
                    companyWelfare = JSONObject.parseObject(welfareIds.get(0));
                }
            }
        }
        if (null != companyWelfareDTO.getHouseId()) {
            HouseResource houseResource = houseResourceDao.selectById(companyWelfareDTO.getHouseId());
            if (null != houseResource && StringUtils.isNotNull(houseResource.getPayType())) {
                houseWelfare = new JSONObject();
                houseWelfare = JSONObject.parseObject(houseResource.getPayType());
                houseWelfare.put("remitPercent", 0);
            }
        }
        if (null != companyWelfare) {
            companyWelfare.put("type", "company");
            return companyWelfare;
        } else if (null != personWelfare) {
            personWelfare.put("type", "person");
            return personWelfare;
        } else if (null != houseWelfare) {
            houseWelfare.put("type", "house");
            return houseWelfare;
        } else {
            throw new InfoException("暂无任何福利!");
        }
    }

    @Override
    public ProductList selectById(Integer productId) {
        return productListDAO.selectById(productId);
    }

    @Override
    public String addProduct(ProductList bean) {
        if (!StringUtils.isEmpty(bean.getAddress())) {
            String position = LbsAmapUtils.formatPosition(bean.getAddress());
            bean.setPosition(position);
        }
        productListDAO.insertRequired(bean);
        return bean.getProductId() == null ? "" : bean.getProductId().toString();
    }

    @Override
    public Long getSizeByCondition(ProductBean bean) {
        Long size = productListDAO.getSizeByCondition(bean);
        return size;
    }

    @Override
    public String updateProduct(ProductList bean) {
        AssertUtils.isNotNull(bean.getProductId(), "更新标识未知");
        try {
            int row = productListDAO.updateRequired(bean);
        } catch (Exception e) {
            log.error("更新产品出错:" + e);
            return Boolean.FALSE.toString();
        }
        return Boolean.TRUE.toString();
    }

    /**
     * @param welfare
     * @param productId
     * @return
     */
    @Override
    public int updateWelfare(CompanyWelfareNotice welfare, Integer productId) {
        if (welfare.getRemitPercent() < 0 || welfare.getRemitPercent() > 100) {
            throw new InfoException("房租减免必须在（0~100）%");
        }
        if (welfare.getMortagageNum() < 0) {
            throw new InfoException("首次支付押金的月数必须大于0");
        }
        if (welfare.getPayMentNum() < 0) {
            throw new InfoException("首次支付支付的月数必须大于0");
        }
        ProductList product = productListDAO.selectById(productId);
        AssertUtils.isNotNull(product, "更新的产品标识非法！！");
        ProductList persistentProduct = new ProductList();
        persistentProduct.setProductId(product.getProductId());
        persistentProduct.setHasPersonal(true);
        persistentProduct.setPersonalWelfare(JSON.toJSONString(welfare));
        return productListDAO.updateRequired(persistentProduct);
    }

    @Override
    public int removeWelfare(Integer productId) {
        ProductList product = productListDAO.selectById(productId);
        AssertUtils.isNotNull(product, "更新的产品标识非法！");
        ProductList persistentProduct = new ProductList();
        persistentProduct.setProductId(product.getProductId());
        persistentProduct.setHasPersonal(false);
        return productListDAO.updateRequired(persistentProduct);
    }

    @Override
    public List<ProductCompanyWelfare> listCompanyWelfare(ProductCompanyWelfareDTO productCompanyWelfareDTO) {
        AssertUtils.isNotNull(productCompanyWelfareDTO.getProductId(), "产品id不能为空！");
        if (!productCompanyWelfareDTO.getAdd()) {//列出该产品的关联的企业福利
            //列出已添加企业福利
            //查询该产品的关联的企业福利
            List<ProductCompanyWelfare> productCompanyWelfare = productRelationWelfareDao.listWelfareByProductId(productCompanyWelfareDTO);
            for (ProductCompanyWelfare welfare : productCompanyWelfare) {
                CompanyList companyList = companyListDao.selectById(welfare.getCompanyId());
                if (null != companyList) {
                    welfare.setCompanyName(companyList.getCompanyName());
                }
                welfare.setProductId(productCompanyWelfareDTO.getProductId());
            }
            return productCompanyWelfare;
        } else {//列出未添加的企业福利，一个企业只能添加一次，要过滤
            //列出要添加的企业福利-一个企业只能添加一次
            List<ProductCompanyWelfare> result = new ArrayList<ProductCompanyWelfare>();
            //该产品已关联过福利的的企业id
            List<Integer> donotNeedCompanyIds = productRelationWelfareDao.selectHasAddCompanyId(productCompanyWelfareDTO.getProductId());
            List<CompanyWelfare> welfareList = null;
            if (donotNeedCompanyIds.size() > 0) {
                productCompanyWelfareDTO.setDontNeedCompanyId(donotNeedCompanyIds);
                welfareList = companyWelfareDao.listOppositeUnSelected(productCompanyWelfareDTO);
            } else {
                welfareList = companyWelfareDao.listAllCompanyWelfare(productCompanyWelfareDTO);
            }
            for (CompanyWelfare item : welfareList) {
                ProductCompanyWelfare itemResult = new ProductCompanyWelfare();
                itemResult.setCompanyId(item.getCompanyId());
                CompanyList companyList = companyListDao.selectById(item.getCompanyId());
                if (null != companyList) {
                    itemResult.setCompanyName(companyList.getCompanyName());
                }
                itemResult.setWelfareId(item.getWelfareId());
                itemResult.setContent(item.getWelfareContent());
                itemResult.setCreateTime(item.getUpdateTime());
                result.add(itemResult);
            }
            return result;
        }
    }

    @Override
    public Long countCompanyWelfare(ProductCompanyWelfareDTO productCompanyWelfareDTO) {
        Long count = 0l;
        if (null != productCompanyWelfareDTO.getProductId()) {
            count = productRelationWelfareDao.countWelfareByProductId(productCompanyWelfareDTO);
        } else {
            count = companyWelfareDao.countUnSelected();
        }
        if (null == count) {
            return 0l;
        } else {
            return count;
        }
    }

    @Transactional
    public int addCompanyWelfare(Integer productId, String companyWelfareIds) {
        AssertUtils.isNotNull(productId, "企业标识未知！");
        AssertUtils.isNotNull(companyWelfareIds, "企业福利标识未知！");
        AssertUtils.isNotNull(productListDAO.selectById(productId), "产品ID未找到！");
        String[] companyWelfareIdArr = companyWelfareIds.split(",");
        List<String> validWelfareIdList = new ArrayList<String>();
        if (companyWelfareIdArr.length > 0) {
            Collections.addAll(validWelfareIdList, companyWelfareIdArr);
            if (0 == validWelfareIdList.size()) {
                validWelfareIdList = null;
            }
            List<String> validFinalWelfareIdList = companyWelfareDao.selectByIds(validWelfareIdList);
            if (validFinalWelfareIdList.size() > 0) {
                Iterator<String> iterator = validFinalWelfareIdList.iterator();
                while (iterator.hasNext()) {
                    if (null != productRelationWelfareDao.selectById(Integer.valueOf(iterator.next()), productId)) {
                        iterator.remove();
                    }
                }
                if (validFinalWelfareIdList.size() > 0) {
                    productRelationWelfareDao.insertBatch(validFinalWelfareIdList, productId);
                    companyWelfareDao.updateBatchSelectedStatus(validFinalWelfareIdList);
                } else {
                    return 0;
                }
                return validFinalWelfareIdList.size();
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    @Transactional
    public int deleteCompanyWelfare(Integer productId, String companyWelfareIds) {
        AssertUtils.isNotNull(productId, "企业标识未知！");
        AssertUtils.isNotNull(companyWelfareIds, "企业福利标识未知！");
        AssertUtils.isNotNull(productListDAO.selectById(productId), "产品ID未找到！");
        String[] args = companyWelfareIds.split(",");
        List<String> list = new ArrayList<String>();
        Collections.addAll(list, args);
        if (list.size() > 0) {
            productRelationWelfareDao.deleteBatch(list, productId);
            companyWelfareDao.updateBatchUnSelectStatus(list);
        }
        return 1;
    }


    @Override
    public List<BasicFacilities> getBasicList(Integer productId) {
        BasicBean basic = new BasicBean();
        basic.setProductId(productId);
        List<ProductRalationBasic> relationList = productRalationBasicDAO.getListByCondition(basic);
        List<BasicFacilities> basicList = new ArrayList<BasicFacilities>();
        if (relationList != null && relationList.size() > 0) {
            for (ProductRalationBasic productRalationBasic : relationList) {
                Integer basicId = productRalationBasic.getBasicId();
                BasicFacilities basicObj = basicFacilitiesDAO.selectById(basicId);
                basicList.add(basicObj);
            }
        }
        return basicList;
    }
}
