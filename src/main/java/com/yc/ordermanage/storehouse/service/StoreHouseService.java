package com.yc.ordermanage.storehouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.ordermanage.storehouse.dao.StoreHouseRepository;
import com.yc.ordermanage.storehouse.domain.StoreHouseVO;

@Service
public class StoreHouseService {

	@Autowired
	private StoreHouseRepository storeHouseRepository;
	
	public StoreHouseVO selectById(Long id) {
		return storeHouseRepository.selectById(id);
	}
	
	@Transactional
	public Boolean deleteById(Long id) {
		storeHouseRepository.deleteById(id);
		return true;
	}

	public List<StoreHouseVO> selectAll() {
		// TODO Auto-generated method stub
		return storeHouseRepository.selectAll();
	}

	public List<StoreHouseVO> findStoreHouseByCondition(String name_spec_color) {
		// TODO Auto-generated method stub
		return storeHouseRepository.findStoreHouseByCondition(name_spec_color);
	}

	public void save(StoreHouseVO storeHouseVO) {
		storeHouseRepository.save(storeHouseVO);
	}
}
