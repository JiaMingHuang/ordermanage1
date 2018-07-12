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

	@Transactional
	public void updateOrderDetail(OrderDetailVO orderDetailVO) {
		orderDetailVO.setUpdatedate(new Date());
		orderDetailVO.setAmount(orderDetailVO.getAmount() - orderDetailVO.getActual_take_amount());
		orderDetailRepository.updateOrderDetail(orderDetailVO.getUpdatedate(),orderDetailVO.getAmount(),orderDetailVO.getId());
	}

	public static void main(String[] args) {

		String [] arr = new String[]{"2","3","4","5"};
		for (int i = 0; i < arr.length; i++ ){
			for (int j=0;j< arr.length;j++){
				for (int k=0;k< arr.length;k++){
					System.out.println(arr[i] + arr[j] + arr[k]);
				}
			}
		}

	}


	public List<OrderDetailVO> findOrderDetailById(Long factoryid,String delflag) {
		return orderDetailRepository.findOrderDetailById(factoryid,delflag);
	}

	public OrderDetailVO update(OrderDetailVO orderDetail) {
		orderDetail.setDelflag("1");
		return orderDetailRepository.save(orderDetail);
	}
}
