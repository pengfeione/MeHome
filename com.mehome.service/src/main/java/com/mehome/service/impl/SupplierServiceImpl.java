package com.mehome.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.mehome.enumDTO.PayTypeEnum;
import com.mehome.exceptions.InfoException;
import com.mehome.requestDTO.SupplierRequestDTO;
import com.mehome.utils.AssertUtils;
import com.mehome.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mehome.dao.SupplierListDao;
import com.mehome.domain.SupplierList;
import com.mehome.requestDTO.SupplierBean;
import com.mehome.service.iface.ISupplierService;

@Service("ISupplierSerive")
public class SupplierServiceImpl implements ISupplierService {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private SupplierListDao supplierListDAO;

    @Override
    public List<SupplierBean> getListByCondition(SupplierBean bean) {
        List<SupplierList> supplierList = supplierListDAO.getListByCondition(bean);
        List<SupplierBean> supplierBeanList = new ArrayList<SupplierBean>();
        if (supplierList != null && supplierList.size() > 0) {
            for (SupplierList supplier : supplierList) {
                SupplierBean newBean = new SupplierBean(supplier);
                supplierBeanList.add(newBean);
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

    @Override
    public Long getSizeByCondition(SupplierBean bean) {
        Long size = supplierListDAO.getSizeByCondition(bean);
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
        if (PayTypeEnum.contain(record.getRecipientType())
                && !PayTypeEnum.CASH.getKey().equals(record)
                && StringUtils.isNull(record.getRecipientAccount())) {
            throw new InfoException("非现金支付方式账户不能为空！");
        }

        if (null != record.getRentOnline()
                && record.getRentOnline()
                && StringUtils.isNull(record.getRecipientAccount())) {
            throw new InfoException("非现金支付方式账户不能为空！");
        }
        if (null != record.getRentPercent() && (record.getRentPercent().doubleValue() > 100.0d || record.getRentPercent().doubleValue() < 0d)) {
            throw new InfoException("租金收款比例不合法");
        }
        if (null != record.getDepositPercent() && (record.getDepositPercent().doubleValue() > 100.0d || record.getDepositPercent().doubleValue() < 0d)) {
            throw new InfoException("押金收款比例不合法");
        }
        record.setCreateTime(null);
        record.setUpdateTime(null);
        return supplierListDAO.updateRequired(record);
    }

    @Override
    public SupplierList selectById(Integer supplierListId) {
        AssertUtils.isNotNull(supplierListId, "查询标识不能为空！");
        return supplierListDAO.selectById(supplierListId);
    }

    @Override
    public int insertRequired(SupplierList record) {
        AssertUtils.isNotNull(record.getSupplierPhone(), "供应商电话不能为空！");
        AssertUtils.isNotNull(record.getRecipientType(), "收款方式未知！");
        if (!PayTypeEnum.contain(record.getRecipientType())) {
            throw new InfoException("收款方式不合法！");
        }
        if (PayTypeEnum.contain(record.getRecipientType())
                && !PayTypeEnum.CASH.getKey().equals(record)
                && StringUtils.isNull(record.getRecipientAccount())) {
            throw new InfoException("非现金支付方式账户不能为空！");
        }

        if (null != record.getRentOnline()
                && record.getRentOnline()
                && StringUtils.isNull(record.getRecipientAccount())) {
            throw new InfoException("非现金支付方式账户不能为空！");
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
        return record.getSupplierId();
    }

    @Override
    public List<SupplierList> selectByCondition(SupplierRequestDTO supplier) {
        return supplierListDAO.selectByCondition(supplier);
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
