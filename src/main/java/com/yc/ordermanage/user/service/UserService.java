package com.yc.ordermanage.user.service;

import com.yc.ordermanage.common.util.DESUtil;
import com.yc.ordermanage.user.dao.UserRepository;
import com.yc.ordermanage.user.domain.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
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
	public Boolean deleteUserByid(Long id) {
		userRepository.deleteById(id);
		return true;
	}

	@Transactional
	public UserVO createUser(UserVO userVO){
		userVO.setPassword(DESUtil.encryptBasedDes(userVO.getPassword()));
		userVO.setCreatedate(new Date());
		userVO.setUpdatedate(new Date());
		return userRepository.save(userVO);
	}

	public Optional<UserVO> findById(Long id) {
		return userRepository.findById(id);
	}

	/**
	 * 修改用户信息
	 * 注：仅修改部分信息
	 */
	public UserVO updateUser(UserVO userVO) {
		UserVO beforeUserVO = findById(userVO.getId()).get();
		beforeUserVO.setUpdatedate(new Date());
		beforeUserVO.setAccounttype(userVO.getAccounttype());
		beforeUserVO.setManagername(userVO.getManagername());
		beforeUserVO.setCompanyname(userVO.getCompanyname());
		beforeUserVO.setContact(userVO.getContact());
		beforeUserVO.setAddress(userVO.getAddress());
		return userRepository.save(beforeUserVO);
	}
	
	public List<UserVO> getFactory(Integer accounttype){
		return userRepository.getFactory(accounttype);
	}
}
