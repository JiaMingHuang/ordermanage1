package com.yc.ordermanage.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yc.ordermanage.order.domain.OrderModel;
import com.yc.ordermanage.order.domain.OrderVO;
import com.yc.ordermanage.order.service.OrderService;
import com.yc.ordermanage.orderdetail.domain.OrderDetailVO;
import com.yc.ordermanage.orderdetail.service.OrderDetailService;
import com.yc.ordermanage.user.domain.UserVO;
import com.yc.ordermanage.user.service.UserService;

import javax.servlet.http.HttpSession;

@RequestMapping("/order")
@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private UserService userService;
	
	@RequestMapping("/initOrderPage")
	public String initOrderPage() {
		return "/order/order-table";
	}

	@GetMapping("/add")
	public String orderForm(Model model){

		/*Model
		List<UserVO> factory = userService.getFactory(3);
		session.setAttribute("factory", factory);*/
		/*UserVO userVO = new UserVO();
		userVO.setAccounttype(3);*/
		List<UserVO> factory = userService.getFactory(3);
		model.addAttribute("factory", factory);
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
	* @Description: 保存订单和订单商品
	* @param orderVO
	* @return Boolean
	* @author kaming.Van.hwang
	* @date 2018年6月28日上午1:39:34
	 */
	@RequestMapping("/form")
	@ResponseBody
	public Boolean putUser(@RequestBody OrderModel orderModel) {
		OrderVO orderVO = orderService.addOrder(orderModel.getOrderVO());
		List<OrderDetailVO> orderDetailVOList = orderModel.getOrderDetailVOList();
		for (OrderDetailVO orderDetailVO : orderDetailVOList) {
			orderDetailVO.setOrderid(orderVO.getId());
			orderDetailService.addOrderDetail(orderDetailVO);
		}
		return true;
	}
	
	@GetMapping("/alter/{id}")
	public String initOrderAlter(Model model, @PathVariable Long id) {
		model.addAttribute("order", orderService.findById(id).get());
		model.addAttribute("orderdetails", orderDetailService.findListById(id));
		return "/order/order-alert";
	}

	/**
	 * 确认收货
	 * @param id
	 * @return
	 */
	@RequestMapping("/doTakeOver/{id}")
	@ResponseBody
	public String doTakeOver(@PathVariable Long id){
		OrderVO orderVO = orderService.findById(id).get();
		orderVO.setIstakeover("1");
		orderService.updateOrderVO(orderVO);
		return "SUCCESS";
	}

	/**
	 * 确认收款
	 * @param id
	 * @return
	 */
	@RequestMapping("/doGather/{id}")
	@ResponseBody
	public String doGather(@PathVariable Long id){
		OrderVO orderVO = orderService.findById(id).get();
		orderVO.setIsgather("1");//如果确认收款则为1，未收款为0
		orderService.updateOrderVO(orderVO);
		return "SUCCESS";
	}

	/**
	 * 修改订单
	 * @param orderModel
	 * @return
	 */
	@RequestMapping("/updateOrder")
	@ResponseBody
	public String updateOrder(@RequestBody OrderModel orderModel){
		OrderVO orderVO = orderModel.getOrderVO();
		List<OrderDetailVO> orderDetailVOList = orderModel.getOrderDetailVOList();
		orderService.updateOrderVO(orderVO);
		for (OrderDetailVO orderDetailVO : orderDetailVOList) {
			orderDetailService.updateOrderDetail(orderDetailVO);
		}
		return  "SUCCESS";
	}

	/**
	 * 查看未收款订单，即未完成订单
	 * @param model
	 * @return
	 */
	@RequestMapping("/findUnfinishedOrder")
	@ResponseBody
	public List<OrderVO> findUnfinishedOrder(Model model){
		List<OrderVO> orderVOS = orderService.findUnfinishedOrder("0");//搜索未收款的所有订单
		return orderVOS;
	}

	/**
	 * 查看所有已完成订单，即已收款订单
	 */
	@RequestMapping("/findFinishedOrder")
	@ResponseBody
	public List<OrderVO> findFinishedOrder(Model model){
		List<OrderVO> orderVOS = orderService.findFinishedOrder("1");//搜索已收款的所有订单
		return orderVOS;
	}
}
