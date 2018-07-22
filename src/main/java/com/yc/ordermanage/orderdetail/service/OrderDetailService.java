package com.yc.ordermanage.orderdetail.service;

import com.yc.ordermanage.orderdetail.dao.OrderDetailRepository;
import com.yc.ordermanage.orderdetail.domain.OrderDetailVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Service
public class OrderDetailService {

	@Autowired
	private OrderDetailRepository orderDetailRepository;
	@PersistenceContext
	protected EntityManager entityManager;
	
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
		OrderDetailVO temp = findOneById(orderDetailVO.getId());
		temp.setUpdatedate(new Date());
		//orderDetailVO.setAmount(orderDetailVO.getAmount() - orderDetailVO.getActual_take_amount());
		//orderDetailRepository.updateOrderDetail(orderDetailVO.getUpdatedate(),orderDetailVO.getAmount(),orderDetailVO.getId());
		temp.setActual_take_amount(orderDetailVO.getActual_take_amount());
		temp.setActual_take_total(orderDetailVO.getActual_take_amount());
		temp.setFactory_not_delivery_amount(orderDetailVO.getActual_take_amount());
		temp.setFactory_not_delivery_totalnumber(orderDetailVO.getActual_take_amount());
		temp.setFactory_not_delivery_total(orderDetailVO.getActual_take_amount());
		temp.setStorehouse_actual_take_amount(orderDetailVO.getActual_take_amount());
		temp.setStorehouse_actual_take_totalnumber(orderDetailVO.getActual_take_amount());
		temp.setStorehouse_actual_take_total(orderDetailVO.getActual_take_amount());
		orderDetailRepository.save(temp);
	}

	private OrderDetailVO findOneById(Long id) {
		return orderDetailRepository.findOneById(id);
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

	@Transactional
    public void batchInsert(List<OrderDetailVO> orderDetailVOList) {
		for (int i=0; i<orderDetailVOList.size(); i++){
			entityManager.persist(orderDetailVOList.get(i));
			if(i % 50 == 0){
				entityManager.flush();
				entityManager.clear();
			}
		}
    }

	@Transactional
	public void batchDelete(List<OrderDetailVO> orderDetailVOList) {
		for (int i = 0; i < orderDetailVOList.size(); i++) {
			entityManager.remove(orderDetailVOList.get(i));
			if (i % 50 == 0) {
				entityManager.flush();
				entityManager.clear();
			}
		}
	}
}
