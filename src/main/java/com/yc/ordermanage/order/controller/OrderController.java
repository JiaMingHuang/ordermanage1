package com.yc.ordermanage.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/order")
@Controller
public class OrderController {

	@RequestMapping("/page")
	public String initOrderPage() {
		return "/order/order";
	}

	@RequestMapping("/orderForm")
	public String orderForm(){
		return "/order/order-form";
	}
}
