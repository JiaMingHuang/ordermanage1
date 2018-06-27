package com.yc.ordermanage.order.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.yc.ordermanage.order.domain.OrderVO;

public interface OrderRepository extends JpaRepository<OrderVO, Long> {

	@Modifying
	public void deleteById(Long id);

	/**
	 * 查询三个月内未下单客户
	 */
	@Query(value = "SELECT DISTINCT A.CLIENTNAME FROM T_ORDER A WHERE NOT EXISTS (SELECT 1 FROM T_ORDER B WHERE A.CLIENTNAME = B.CLIENTNAME AND B.CREATEDATE >= SUBDATE(CURDATE(),INTERVAL 3 MONTH))", nativeQuery = true)
	public List<String> findNoOrderClientname();
}
