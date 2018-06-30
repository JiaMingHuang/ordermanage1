package com.yc.ordermanage.orderdetail.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.yc.ordermanage.orderdetail.domain.OrderDetailVO;

public interface OrderDetailRepository extends JpaRepository<OrderDetailVO, Long>{

	@Modifying
	@Override
	public OrderDetailVO save(OrderDetailVO orderDetailVO);

	@Query(value = "SELECT T.* FROM ORDERDETAIL T WHERE ORDERID = :orderid", nativeQuery = true)
	public List<OrderDetailVO> findListById(@Param("orderid") Long orderid);
	
	
}
