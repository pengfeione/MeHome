package com.mehome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mehome.requestDTO.OrderBean;
import com.mehome.service.iface.IOrderService;
import com.mehome.utils.Result;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Value("${cros}")
    private String cros;
    @Autowired
    private IOrderService orderService;

    /**
     * 订单列表
     *
     * @param order
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public ResponseEntity<Result> list(@RequestBody OrderBean order) {
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
    @GetMapping("/placeOrder")
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
    @GetMapping("/payOrder")
    @ResponseBody
    public ResponseEntity<Result> payOrder(@RequestBody OrderBean order) {
        return ResponseEntity
                .ok()
                .header("Access-Control-Allow-Origin", cros)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(orderService.payOrder(order)));
    }

    /**
     * 修改订单
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
}
