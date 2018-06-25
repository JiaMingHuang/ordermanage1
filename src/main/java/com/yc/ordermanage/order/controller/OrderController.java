package com.yc.ordermanage.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.ordermanage.order.domain.OrderVO;
import com.yc.ordermanage.order.service.OrderService;
@RequestMapping("/order")
@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/initOrderPage")
	public String initOrderPage() {
		return "/order/order-table";
	}

	@RequestMapping("/orderForm")
	public String orderForm(){
		return "/order/order-form";
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public List<OrderVO> findAll(){
		List<OrderVO> orderVOs = orderService.findAll();
		return orderVOs;
	}
}
