package com.mehome.service.iface;

import java.util.List;

import com.mehome.requestDTO.RecommendProductBean;

public interface IRecommendService {
	public List<RecommendProductBean> getListByCondition(RecommendProductBean bean);
	
	public Long getSizeByCondition(RecommendProductBean bean);
}
