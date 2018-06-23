package com.yc.ordermanage.user.service;

import com.yc.ordermanage.user.dao.UserRepository;
import com.yc.ordermanage.user.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<UserVO> findAll() {
		return userRepository.findAll();
	}

	public List<UserVO> findByUserid(String userid) {
		return userRepository.getUserVOByUserid(userid);
	}

	@Transactional
	public Boolean deleteUserByid(String id) {
		userRepository.deleteById(id);
		return true;
	}

	public Optional<UserVO> findById(String id) {
		return userRepository.findById(id);
	}

}
