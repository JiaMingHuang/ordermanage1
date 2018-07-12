package com.yc.ordermanage.order.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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
		return "order/order-table";
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
		return "order/order-form";
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
			orderDetailVO.setOrdernumber(orderVO.getOrdernumber());
			orderDetailVO.setDelflag("0");
			orderDetailService.addOrderDetail(orderDetailVO);
		}
		return true;
	}
	
	@GetMapping("/alter/{id}")
	public String initOrderAlter(Model model, @PathVariable Long id) {
		model.addAttribute("order", orderService.findById(id).get());
		model.addAttribute("orderdetails", orderDetailService.findListById(id));
		return "order/order-alert";
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
		orderService.updateOrderVO(new Date(), id);
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

		//订单完成时订单下的商品列表也完成 暂射delFlag为1 即订单已完成\
		List<OrderDetailVO> orderDetails = orderDetailService.findListById(orderVO.getId());
		for (OrderDetailVO orderDetail : orderDetails) {
			orderDetailService.update(orderDetail);
		}
		orderService.updateOrderVO(new Date(), id);
		return "SUCCESS";
	}

	/**
	 * 修改订单
	 * @param orderModel
	 * @return
	 */
	@PostMapping("/updateOrder")
	@ResponseBody
	public Boolean updateOrder(@RequestBody OrderModel orderModel){
		OrderVO orderVO = orderModel.getOrderVO();
		List<OrderDetailVO> orderDetailVOList = orderModel.getOrderDetailVOList();
		orderService.updateOrderVO(new Date(), orderVO.getId());//更新主订单修改时间
		for (OrderDetailVO orderDetailVO : orderDetailVOList) {//更新订单中的商品列表
			orderDetailService.updateOrderDetail(orderDetailVO);
		}
		return true;
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
