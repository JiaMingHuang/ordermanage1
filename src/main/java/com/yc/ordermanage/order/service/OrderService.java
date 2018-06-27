package com.yc.ordermanage.order.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.ordermanage.order.dao.OrderRepository;
import com.yc.ordermanage.order.domain.OrderVO;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public List<OrderVO> findAll() {
		return orderRepository.findAll();
	}

	@Transactional
	public Boolean deleteById(Long id) {
		orderRepository.deleteById(id);
		return true;
	}

	/**
	 * 获取三个月内未下单客户
	 */
	public List<String> findNoOrderClientname(){
		return orderRepository.findNoOrderClientname();
	}

	/**
	* @Title: addOrder 
	* @Description: TODO 
	* @param orderVO
	* @return OrderVO
	* @author kaming.Van.hwang
	* @date 2018年6月28日上午1:43:24
	 */
	@Transactional
	public OrderVO addOrder(OrderVO orderVO) {
		orderVO.setCreatedate(new Date());
		orderVO.setUpdatedate(new Date());
		return orderRepository.save(orderVO);
	}

}


