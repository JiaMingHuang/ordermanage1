package com.yc.ordermanage.order.service;

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

}


