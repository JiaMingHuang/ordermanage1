package com.yc.ordermanage.order.domain;

import java.util.List;

import com.yc.ordermanage.orderdetail.domain.OrderDetailVO;

public class OrderModel {

	private OrderVO orderVO;
	
	private List<OrderDetailVO> orderDetailVOList;

	public OrderVO getOrderVO() {
		return orderVO;
	}

	public void setOrderVO(OrderVO orderVO) {
		this.orderVO = orderVO;
	}

	public List<OrderDetailVO> getOrderDetailVOList() {
		return orderDetailVOList;
	}

	public void setOrderDetailVOList(List<OrderDetailVO> orderDetailVOList) {
		this.orderDetailVOList = orderDetailVOList;
	}
}
