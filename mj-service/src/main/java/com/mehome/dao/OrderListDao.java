package com.mehome.dao;

import com.mehome.domain.OrderList;

public interface OrderListDao {
    int delete(String orderId);

    int insert(OrderList record);

    int insertRequired(OrderList record);

    OrderList selectById(String orderId);

    int updateRequired(OrderList record);

    int update(OrderList record);
}