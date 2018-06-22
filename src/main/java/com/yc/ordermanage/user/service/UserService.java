package com.yc.ordermanage.user.service;

import com.yc.ordermanage.user.dao.UserRepository;
import com.yc.ordermanage.user.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserVO> findAll(){
		return userRepository.findAll();
	}

	public List<UserVO> findByUserid(String userid) {
		return userRepository.getUserVOByUserid(userid);
	}

}
