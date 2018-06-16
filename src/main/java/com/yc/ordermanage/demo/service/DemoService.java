package com.yc.ordermanage.demo.service;

import com.yc.ordermanage.demo.dao.DemoRepository;
import com.yc.ordermanage.demo.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoService {

	@Autowired
	private DemoRepository demoRepository;

	public List<UserVO> findAll() {
		return demoRepository.findAll();
	}

	public UserVO findOne(String id) {
		return demoRepository.getBySQL(id);
	}

}
