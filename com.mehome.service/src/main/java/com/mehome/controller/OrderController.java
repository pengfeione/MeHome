package com.mehome.controller;

import com.alibaba.fastjson.JSONObject;
import com.mehome.domain.HouseResource;
import com.mehome.requestDTO.OrderBean;
import com.mehome.requestDTO.ThirdPayMentBean;
import com.mehome.service.iface.IOrderService;
import com.mehome.utils.AlipayProperties;
import com.mehome.utils.DateUtils;
import com.mehome.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Value("${cros}")
    private String cros;
    @Autowired
    private IOrderService orderService;
    @Autowired
	private AlipayProperties alipayProperties;
    /**
     * 订单列表
     *
     * @param order
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public ResponseEntity<Result> list(@RequestBody OrderBean order) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE), 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date nowDate = cal.getTime();
        if (order.getDays() != null) {
            Date endTimeEnd = DateUtils.getDayEnd(new Date(), "day", order.getDays());
            order.setEndTimeDateBegin(nowDate);
            order.setEndTimeDateEnd(endTimeEnd);
        }
        if (order.getMonths() != null) {
            Date endTimeEnd = DateUtils.getDayEnd(new Date(), "month", order.getMonths());
            order.setEndTimeDateBegin(nowDate);
            order.setEndTimeDateEnd(endTimeEnd);
        }
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(orderService.getListByCondition(order), orderService.getSizeByCondition(order)));
    }

    /**
     * 下单
     *
     * @param order
     * @return
     */
    @PostMapping("/placeOrder")
    @ResponseBody
    public ResponseEntity<Result> placeOrder(@RequestBody OrderBean order) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(orderService.placeOrder(order)));
    }

    /**
     * 支付订单
     *
     * @param order
     * @return
     */
    @PostMapping("/payOrder")
    @ResponseBody
    public ResponseEntity<Result> payOrder(@RequestBody OrderBean order) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(orderService.payOrder(order)));
    }

    /**
     * 修改订单（包括修改租赁日期，托管，状态更改)
     *
     * @param order
     * @return
     */
    @PostMapping("/updateOrder")
    @ResponseBody
    public ResponseEntity<Result> updateOrder(@RequestBody OrderBean order) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(orderService.updateOrder(order)));
    }

    /**
     * 退款
     *
     * @param order
     * @return
     */
    @PostMapping("/refundOrder")
    @ResponseBody
    public ResponseEntity<Result> refundOrder(@RequestBody OrderBean order) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(orderService.refundOrder(order)));
    }

    /**
     * 判断预约  返回true代表可以预约
     *
     * @param order
     * @return
     */
    @PostMapping("/judgeExistOrder")
    @ResponseBody
    public ResponseEntity<Result> judgeExistOrder(@RequestBody OrderBean order) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(orderService.judgeExistOrder(order)));
    }

    /**
     * 返回订单的租赁时间
     *
     * @param order
     * @return
     */
    @PostMapping("/piece_by_order")
    @ResponseBody
    public ResponseEntity<Result> piece_by_order(@RequestBody OrderBean order) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(orderService.houseTimePiece(order)));
    }

    /**
     * 返回订单的租赁时间
     *
     * @param house
     * @return
     */
    @PostMapping("/piece_by_house")
    @ResponseBody
    public ResponseEntity<Result> piece_by_house(@RequestBody HouseResource house) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(orderService.pieceByHouse(house)));
    }

    @PostMapping("/payment_create_order")
    @ResponseBody
    public ResponseEntity<Result> paymentCreateOrder(@RequestBody ThirdPayMentBean bean, HttpServletRequest request) {
        String clientIp = request.getHeader("x-real-ip");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Access-Control-Allow-Origin", "http://m.mjiahome.com");
        httpHeaders.add("Access-Control-Allow-Methods", "GET, POST");
        
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(orderService.paymentCreateOrder(bean)));
    }
    
    @PostMapping("/alipay_create_order")
    @ResponseBody
    public void alipayCreateOrder(@RequestBody ThirdPayMentBean bean, HttpServletRequest request,HttpServletResponse response) {
    	
    	JSONObject ret=orderService.paymentCreateOrder(bean);
    	String form=ret.getString("retStr");
        response.setContentType("text/html;charset=" + alipayProperties.getCharset()); 
	    try {
	    	//直接将完整的表单html输出到页面 
			response.getWriter().write(form);
			response.getWriter().flush(); 
		    response.getWriter().close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
    }
}
