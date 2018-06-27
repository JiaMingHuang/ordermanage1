package com.yc.ordermanage.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.ordermanage.order.domain.OrderVO;
import com.yc.ordermanage.order.service.OrderService;
import com.yc.ordermanage.user.domain.UserVO;
import com.yc.ordermanage.user.service.UserService;
@RequestMapping("/order")
@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/initOrderPage")
	public String initOrderPage() {
		return "/order/order-table";
	}

	@RequestMapping("/add")
	public String orderForm(){
		//model.addAttribute("factory", userService.getFactory(3));
		return "/order/order-form";
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public List<OrderVO> findAll(){
		List<OrderVO> orderVOs = orderService.findAll();
		return orderVOs;
	}
	
	@DeleteMapping("/deleteById/{id}")
	@ResponseBody
	public Boolean deleteById(@PathVariable Long id) {
		return orderService.deleteById(id);
	}
	
	/**
	* @Title: putUser 
	* @Description: TODO 
	* @param orderVO
	* @return Boolean
	* @author kaming.Van.hwang
	* @date 2018年6月28日上午1:39:34
	 */
	@PutMapping("/form")
	@ResponseBody
	public Boolean putUser(OrderVO orderVO) {
		orderService.addOrder(orderVO);
		return true;
	}
}
