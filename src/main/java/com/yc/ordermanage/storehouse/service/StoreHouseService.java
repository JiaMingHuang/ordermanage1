package com.yc.ordermanage.storehouse.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

	public Optional<StoreHouseVO> findById(Long id) {
		return storeHouseRepository.findById(id);
	}

	public StoreHouseVO updateStoreHouse(StoreHouseVO storeHouseVO) {
		StoreHouseVO shVO = findById(storeHouseVO.getId()).get();
		shVO.setName_spec_color(storeHouseVO.getName_spec_color());
		shVO.setAmount(storeHouseVO.getAmount());
		shVO.setUpdateDate(new Date());
		return storeHouseRepository.save(shVO);
	}
}
