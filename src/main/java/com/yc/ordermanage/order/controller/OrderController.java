package com.yc.ordermanage.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OrderController {

	@RequestMapping("/order-page")
	public String index() {
		return "/order/order";
	}

}
