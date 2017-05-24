package com.mehome.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mehome.dao.RecommendProductDao;
import com.mehome.domain.RecommendProduct;
import com.mehome.requestDTO.RecommendProductBean;
import com.mehome.service.iface.IRecommendService;
@Service("IRecommendService")
public class RecommendServiceImpl implements IRecommendService {
	private Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private RecommendProductDao recommendProductDAO;
	@Override
	public List<RecommendProductBean> getListByCondition(RecommendProductBean bean) {
		List<RecommendProduct> recommendList=recommendProductDAO.getListByCondition(bean);
		List<RecommendProductBean> recommendBeanList=new ArrayList<RecommendProductBean>();
		if(recommendList!=null&&recommendList.size()>0){
			for (RecommendProduct comment : recommendList) {
				RecommendProductBean newBean=new RecommendProductBean(comment);
				recommendBeanList.add(newBean);
			}
		}
		return recommendBeanList;
	}

	@Override
	public Long getSizeByCondition(RecommendProductBean bean) {
		// TODO Auto-generated method stub
		return recommendProductDAO.getSizeByCondition(bean);
	}

}
