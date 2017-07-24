package com.mehome.service.iface;

import com.alibaba.fastjson.JSONObject;
import com.mehome.domain.HouseResource;
import com.mehome.requestDTO.OrderBean;
import com.mehome.requestDTO.ThirdPayMentBean;
import com.mehome.resonpseDTO.HouseTimePiece;

import java.util.List;

public interface IOrderService {

    public List<OrderBean> getListByCondition(OrderBean bean);

    public String placeOrder(OrderBean bean);

    public String payOrder(OrderBean bean);

    public Long getSizeByCondition(OrderBean bean);

    public String updateOrder(OrderBean bean);

    /**
     * 计算福利以及房租、押金
     *
     * @param bean
     * @return
     */
    public OrderBean calculateWelfare(OrderBean bean, Integer calculateMonth);

    public String refundOrder(OrderBean bean);

    public String judgeExistOrder(OrderBean bean);


    List<HouseTimePiece> houseTimePiece(OrderBean bean);

    List<HouseTimePiece> pieceByHouse(HouseResource bean);

    public void payNotify(ThirdPayMentBean thirdPay);

    public JSONObject paymentCreateOrder(ThirdPayMentBean bean);
}
