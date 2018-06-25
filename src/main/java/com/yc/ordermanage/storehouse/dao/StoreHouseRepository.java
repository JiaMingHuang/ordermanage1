package com.yc.ordermanage.storehouse.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yc.ordermanage.storehouse.domain.StoreHouseVO;

@Repository
public interface StoreHouseRepository extends JpaRepository<StoreHouseVO, Long>{

	@Query(value = "SELECT T.* FROM STOREHOUSE T WHERE ID = ?1", nativeQuery = true)
	public StoreHouseVO selectById(Long id);
	
	@Modifying
	public void deleteById(Long id);

	@Query(value = "SELECT T.* FROM STOREHOUSE T", nativeQuery = true)
	public List<StoreHouseVO> selectAll();

	@Query(value = "SELECT T.* FROM STOREHOUSE T WHERE T.NAME_SPEC_COLOR = :name_spec_color", nativeQuery = true)
	public List<StoreHouseVO> findStoreHouseByCondition(@Param("name_spec_color") String name_spec_color);
}
