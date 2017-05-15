package com.mehome.service.impl;

import java.util.ArrayList;
import java.util.List;

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
		List<SupplierList> supplierList=supplierListDAO.getListByCondition(bean);
		List<SupplierBean> supplierBeanList=new ArrayList<SupplierBean>();
		if(supplierList!=null&&supplierList.size()>0){
			for (SupplierList supplier : supplierList) {
				SupplierBean newBean=new SupplierBean(supplier);
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

}
