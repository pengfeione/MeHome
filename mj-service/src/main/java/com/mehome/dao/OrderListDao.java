package com.mehome.dao;

import java.util.List;

import com.mehome.domain.OrderList;
import com.mehome.requestDTO.OrderBean;

public interface OrderListDao {
    int delete(String orderId);

    int insert(OrderList record);

    int insertRequired(OrderList record);

    OrderList selectById(String orderId);

    int updateRequired(OrderList record);

    int update(OrderList record);
    
    public List<OrderList> listAdByCondition(OrderBean bean);
}