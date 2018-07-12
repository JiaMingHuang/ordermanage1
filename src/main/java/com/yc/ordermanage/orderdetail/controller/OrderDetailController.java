package com.yc.ordermanage.orderdetail.controller;

import com.yc.ordermanage.order.domain.OrderVO;
import com.yc.ordermanage.orderdetail.domain.OrderDetailVO;
import com.yc.ordermanage.orderdetail.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("/orderdetail")
@Controller
public class OrderDetailController {
	@Autowired
	private OrderDetailService orderDetailService;

	@RequestMapping("/findOrderDetailById/{factoryid}")
	@ResponseBody
	public List<OrderDetailVO> findOrderDetailById(@PathVariable Long factoryid){
		String delflag = "0";
		List<OrderDetailVO> orderDetails = orderDetailService.findOrderDetailById(factoryid,delflag);//搜索该厂家的订单
		return orderDetails;
	}
}
