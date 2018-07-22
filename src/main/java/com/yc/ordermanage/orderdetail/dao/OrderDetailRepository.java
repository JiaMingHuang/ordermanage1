package com.yc.ordermanage.orderdetail.dao;

import com.yc.ordermanage.orderdetail.domain.OrderDetailVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetailVO, Long>{

	@Modifying
	@Override
	public OrderDetailVO save(OrderDetailVO orderDetailVO);

	@Query(value = "SELECT T.* FROM ORDERDETAIL T WHERE ORDERID = :orderid", nativeQuery = true)
	public List<OrderDetailVO> findListById(@Param("orderid") Long orderid);

	@Modifying
	@Query(value = "UPDATE ORDERDETAIL T SET T.UPDATEDATE = :updatedate,T.AMOUNT = :amount WHERE T.ID = :id", nativeQuery = true)
	public void updateOrderDetail(@Param("updatedate")Date updatedate, @Param("amount")int amount, @Param("id")Long id);

	@Query(value = "SELECT T.* FROM ORDERDETAIL T WHERE FACTORYID = :factoryid and DELFLAG = :delflag", nativeQuery = true)
	public List<OrderDetailVO> findOrderDetailById(@Param("factoryid") Long factoryid, @Param("delflag") String delflag);

	@Query(value = "SELECT T.* FROM ORDERDETAIL T WHERE T.ID = :id", nativeQuery = true)
	public OrderDetailVO findOneById(@Param("id") Long id);
}
