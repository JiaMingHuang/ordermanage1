package com.yc.ordermanage.orderdetail.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.ordermanage.orderdetail.dao.OrderDetailRepository;
import com.yc.ordermanage.orderdetail.domain.OrderDetailVO;

@Service
public class OrderDetailService {

	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Transactional
	public OrderDetailVO addOrderDetail(OrderDetailVO orderDetailVO) {
		orderDetailVO.setCreatedate(new Date());
		orderDetailVO.setUpdatedate(new Date());
		return orderDetailRepository.save(orderDetailVO);
	}
	
	public List<OrderDetailVO> findListById(Long orderid){
		return orderDetailRepository.findListById(orderid);
	}

	public OrderDetailVO updateOrderDetail(OrderDetailVO orderDetailVO) {
		orderDetailVO.setUpdatedate(new Date());
		orderDetailVO.setAmount(orderDetailVO.getAmount() - orderDetailVO.getActual_take_amount());
		orderDetailVO.setTotal(orderDetailVO.getAmount() * orderDetailVO.getPrice());
		return orderDetailRepository.save(orderDetailVO);
	}
}
