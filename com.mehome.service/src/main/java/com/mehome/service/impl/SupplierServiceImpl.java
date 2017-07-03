package com.mehome.service.impl;

import com.mehome.dao.AuthorizeAdminDao;
import com.mehome.dao.SupplierListDao;
import com.mehome.domain.AuthorizeAdmin;
import com.mehome.domain.SupplierList;
import com.mehome.enumDTO.PayTypeEnum;
import com.mehome.enumDTO.RoleEnum;
import com.mehome.exceptions.InfoException;
import com.mehome.requestDTO.SupplierBean;
import com.mehome.requestDTO.SupplierRequestDTO;
import com.mehome.service.iface.ISupplierService;
import com.mehome.utils.AssertUtils;
import com.mehome.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("ISupplierSerive")
public class SupplierServiceImpl implements ISupplierService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private SupplierListDao supplierListDAO;
    @Autowired
    private AuthorizeAdminDao authorizeAdminDao;
    @Value("${default_normal_avatar}")
    private String defaultAvatar;

    @Override
    @Deprecated
    public List<SupplierBean> getListByCondition(SupplierBean bean) {
        List<SupplierList> supplierList = supplierListDAO.getListByCondition(bean);
        List<SupplierBean> supplierBeanList = new ArrayList<SupplierBean>();
        if (supplierList != null && supplierList.size() > 0) {
            for (SupplierList supplier : supplierList) {
                supplierBeanList.add(new SupplierBean(supplier));
            }
        }
        return supplierBeanList;
    }

    @Override
    public String addSupplier(SupplierBean bean) {
        SupplierList supplier = null;
        try {
            supplier = bean.beanToPojo(Boolean.TRUE);
            supplierListDAO.insert(supplier);
        } catch (Exception e) {
            log.error("加入供应商出错:" + e);
        }
        return supplier.getSupplierId() == null ? "" : supplier.getSupplierId().toString();
    }

    @Deprecated
    public Long getSizeByCondition(SupplierBean bean) {
        Long size = supplierListDAO.getSizeByCondition(bean);
        if (null == size) {
            return 0l;
        }
        return size;
    }

    @Override
    public String updateSupplier(SupplierBean bean) {
        SupplierList supplier = null;
        try {
            supplier = bean.beanToPojo(Boolean.FALSE);
            int row = supplierListDAO.updateRequired(supplier);
        } catch (Exception e) {
            log.error("更新供应商出错:" + e);
            return Boolean.FALSE.toString();
        }
        return Boolean.TRUE.toString();
    }

    @Override
    public int updateRequired(SupplierList record) {
        AssertUtils.isNotNull(record.getSupplierId(), "更新标识不能为空！");
        AssertUtils.isNotNull(record.getSupplierPhone(), "供应商电话不能为空！");
        AssertUtils.isNotNull(record.getRecipientType(), "收款方式未知！");
        if (!PayTypeEnum.contain(record.getRecipientType())) {
            throw new InfoException("收款方式不合法！");
        }
        if ((!PayTypeEnum.CASH.getKey().equals(record.getRecipientType()))
                && StringUtils.isNull(record.getRecipientAccount())) {
            throw new InfoException("非现金支付方式账户不能为空！");
        }
        if (null == record.getDepositOnline()) {
            record.setDepositOnline(false);
        }

        //押金线上支付
        if (record.getDepositOnline()
                && (PayTypeEnum.CASH.getKey().equals(record.getRecipientType()))
                ) {
            throw new InfoException("押金线上支付状态与收款方式冲突！");
        }
        if (null == record.getRentOnline()) {
            record.setRentOnline(false);
        }
        //租金线上支付状态
        if (record.getRentOnline()
                && PayTypeEnum.CASH.getKey().equals(record.getRecipientType())) {
            throw new InfoException("租金线上支付状态与收款方式冲突！");
        }
        if (!PayTypeEnum.CASH.getKey().equals(record.getRecipientType())) {
            if ((!record.getRentOnline()) && (!record.getDepositOnline())) {
                throw new InfoException("租金为线上支付，但租金和押金都未开启线上支付！");
            }
        }
        if (null != record.getRentPercent() && (record.getRentPercent().doubleValue() > 100.0d || record.getRentPercent().doubleValue() < 0d)) {
            throw new InfoException("租金收款比例不合法");
        }
        if (null != record.getDepositPercent() && (record.getDepositPercent().doubleValue() > 100.0d || record.getDepositPercent().doubleValue() < 0d)) {
            throw new InfoException("押金收款比例不合法");
        }
        record.setCreateTime(null);
        record.setUpdateTime(null);

        if (StringUtils.isNotNull(record.getAccount())) {
            if (StringUtils.isNull(record.getPassword())) {
                throw new InfoException("管理员密码不能为空！");
            }
            AuthorizeAdmin oldAdmin = authorizeAdminDao.selectBySupplierId(record.getSupplierId());
            if (null == oldAdmin) {
                AuthorizeAdmin authorizeAdmin = new AuthorizeAdmin();
                authorizeAdmin.setAvatar(defaultAvatar);
                authorizeAdmin.setSupplierId(record.getSupplierId());
                authorizeAdmin.setNickName(record.getSupplierName());
                authorizeAdmin.setName(record.getAccount());
                authorizeAdmin.setPassword(record.getPassword());
                authorizeAdmin.setRole(RoleEnum.SUPPLIER.getRole());
                authorizeAdminDao.insertRequired(authorizeAdmin);
            } else {
                if (oldAdmin.getName().equals(record.getAccount()) && oldAdmin.getPassword().equals(record.getPassword())) {
                } else {
                    oldAdmin.setName(record.getAccount());
                    oldAdmin.setPassword(record.getPassword());
                    authorizeAdminDao.updateRequired(oldAdmin);
                }
            }
        }
        return supplierListDAO.updateRequired(record);
    }

    @Override
    public SupplierList selectById(Integer supplierListId) {
        AssertUtils.isNotNull(supplierListId, "查询标识不能为空！");
        return supplierListDAO.selectById(supplierListId);
    }

    @Transactional
    public int insertRequired(SupplierList record) {
        AssertUtils.isNotNull(record.getSupplierPhone(), "供应商电话不能为空！");
        AssertUtils.isNotNull(record.getRecipientType(), "收款方式未知！");
        if (!PayTypeEnum.contain(record.getRecipientType())) {
            throw new InfoException("收款方式不合法！");
        }
        if ((!PayTypeEnum.CASH.getKey().equals(record.getRecipientType()))
                && StringUtils.isNull(record.getRecipientAccount())) {
            throw new InfoException("非现金支付方式账户不能为空！");
        }
        if (null == record.getDepositOnline()) {
            record.setDepositOnline(false);
        }

        //押金线上支付
        if (record.getDepositOnline()
                && (PayTypeEnum.CASH.getKey().equals(record.getRecipientType()))
                ) {
            throw new InfoException("押金线上支付状态与收款方式冲突！");
        }
        if (null == record.getRentOnline()) {
            record.setRentOnline(false);
        }
        //租金线上支付状态
        if (record.getRentOnline()
                && PayTypeEnum.CASH.getKey().equals(record.getRecipientType())) {
            throw new InfoException("租金线上支付状态与收款方式冲突！");
        }
        if (!PayTypeEnum.CASH.getKey().equals(record.getRecipientType())) {
            if ((!record.getRentOnline()) && (!record.getDepositOnline())) {
                throw new InfoException("租金为线上支付，但租金和押金都未开启线上支付！");
            }
        }
        if (null != record.getRentPercent() && (record.getRentPercent().doubleValue() > 100.0d || record.getRentPercent().doubleValue() < 0d)) {
            throw new InfoException("租金收款比例不合法");
        }
        if (null != record.getDepositPercent() && (record.getDepositPercent().doubleValue() > 100.0d || record.getDepositPercent().doubleValue() < 0d)) {
            throw new InfoException("押金收款比例不合法");
        }
        record.setCreateTime(null);
        record.setUpdateTime(null);
        supplierListDAO.insertRequired(record);
        if (StringUtils.isNotNull(record.getAccount())) {
            if (StringUtils.isNull(record.getPassword())) {
                throw new InfoException("管理员密码不能为空！");
            }
            AuthorizeAdmin authorizeAdmin = new AuthorizeAdmin();
            authorizeAdmin.setAvatar(defaultAvatar);
            authorizeAdmin.setSupplierId(record.getSupplierId());
            authorizeAdmin.setNickName(record.getSupplierName());
            authorizeAdmin.setName(record.getAccount());
            authorizeAdmin.setPassword(record.getPassword());
            authorizeAdmin.setRole(RoleEnum.SUPPLIER.getRole());
            authorizeAdminDao.insertRequired(authorizeAdmin);
        }
        return record.getSupplierId();
    }

    @Override
    public List<SupplierList> selectByCondition(SupplierRequestDTO supplier) {
        List<SupplierList> supplierLists = supplierListDAO.selectByCondition(supplier);
        for (SupplierList supllier : supplierLists) {
            AuthorizeAdmin authorizeAdmin = authorizeAdminDao.selectBySupplierId(supllier.getSupplierId());
            if (null != authorizeAdmin) {
                supllier.setAccount(authorizeAdmin.getName());
                supllier.setPassword(authorizeAdmin.getPassword());
            } else {
                supllier.setAccount("");
                supllier.setPassword("");
            }
        }
        return supplierLists;
    }

    @Override
    public Long countByCondition(SupplierRequestDTO supplier) {
        Long ret = supplierListDAO.countByCondition(supplier);
        if (null != ret) {
            return ret;
        } else {
            return 0l;
        }
    }
}
