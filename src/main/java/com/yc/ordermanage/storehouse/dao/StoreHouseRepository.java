package com.yc.ordermanage.storehouse.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.yc.ordermanage.storehouse.domain.StoreHouseVO;

@Repository
public interface StoreHouseRepository extends JpaRepository<StoreHouseVO, Long>{

	@Query(value = "SELECT T.* FROM STOREHOUSE T WHERE ID = ?1", nativeQuery = true)
	public StoreHouseVO selectById(String id);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM STOREHOUSE WHERE ID = ?1", nativeQuery = true)
	public int deleteById(String id);

	@Query(value = "SELECT T.* FROM STOREHOUSE T", nativeQuery = true)
	public List<StoreHouseVO> selectAll();

	@Query(value = "SELECT T.* FROM STOREHOUSE T WHERE (T.NAME_SPEC_COLOR = :NAME_SPEC_COLOR OR :NAME_SPEC_COLOR == NULL)", nativeQuery = true)
	public List<StoreHouseVO> findStoreHouseByCondition(String name_spec_color);
}
