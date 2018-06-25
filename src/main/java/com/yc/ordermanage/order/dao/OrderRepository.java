package com.yc.ordermanage.order.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.yc.ordermanage.order.domain.OrderVO;

public interface OrderRepository extends JpaRepository<OrderVO, Long>{

	@Modifying
	public void deleteById(Long id);
}
