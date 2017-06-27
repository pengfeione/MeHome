package com.mehome.dao;

import com.mehome.domain.OrderList;
import com.mehome.requestDTO.OrderBean;
import com.mehome.resonpseDTO.HouseTimePiece;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderListDao {
    int delete(String orderId);

    int insert(OrderList record);

    int insertRequired(OrderList record);

    OrderList selectById(String orderId);


    /**
     * 查询所有房源id是houseId的所有订单的起始时间和结束时间，排除订单id是orderId的开始时间和结束时间
     *
     * @param houseId
     * @param orderId
     * @return
     */
    List<HouseTimePiece> houseTimePieceExceptMe(@Param("houseId") Integer houseId, @Param("orderId") String orderId);


    List<HouseTimePiece> houseTimePiece(@Param("houseId") Integer houseId);

    int updateRequired(OrderList record);

    int update(OrderList record);

    public List<OrderList> getListByCondition(OrderBean bean);

    Long getSizeByCondition(OrderBean bean);
}