package com.yc.ordermanage.order.dao;

import com.yc.ordermanage.order.domain.OrderVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderVO, Long> {

	@Modifying
	public void deleteById(Long id);

	/**
	 * 查询三个月内未下单客户
	 */
	@Query(value = "SELECT DISTINCT A.CLIENTNAME FROM T_ORDER A WHERE NOT EXISTS (SELECT 1 FROM T_ORDER B WHERE A.CLIENTNAME = B.CLIENTNAME AND B.CREATEDATE >= SUBDATE(CURDATE(),INTERVAL 3 MONTH))", nativeQuery = true)
	public List<String> findNoOrderClientname();
	
	@Modifying
	@Override
	public OrderVO save(OrderVO orderVO);

	@Query(value = "SELECT T.* FROM T_ORDER T WHERE T.ISGATHER = :isgather ORDER BY T.CREATEDATE DESC", nativeQuery = true)
	public List<OrderVO> findUnfinishedOrder(@Param("isgather") String isgather);

	@Query(value = "SELECT T.* FROM T_ORDER T WHERE T.ISGATHER = :isgather", nativeQuery = true)
	public List<OrderVO> findFinishedOrder(@Param("isgather") String isgather);

	@Query(value = "SELECT T.* FROM T_ORDER T WHERE T.ISGATHER = :id", nativeQuery = true)
	public OrderVO findOneById(@Param("id") Long id);

	@Modifying
	@Query(value = "UPDATE T_ORDER T SET T.UPDATEDATE = :updatedate WHERE T.ID = :id", nativeQuery = true)
	public void updateOrderVO(@Param("updatedate") Date updatedate,@Param("id") Long id);
}
