package com.yc.ordermanage.demo.service;

import com.yc.ordermanage.demo.dao.UserRepository;
import com.yc.ordermanage.demo.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserVO> findAll() {
		return userRepository.findAll();
	}

	public UserVO findOne(String id) {
		return userRepository.getBySQL(id);
	}

}
