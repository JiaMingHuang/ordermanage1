package com.yc.ordermanage.user.domain;

import java.util.List;

public class UserModel {

	private UserVO user;
	private List<UserVO> userList;

	public UserVO getUser() {
		return user;
	}

	public void setUser(UserVO user) {
		this.user = user;
	}

	public List<UserVO> getUserList() {
		return userList;
	}

	public void setUserList(List<UserVO> userList) {
		this.userList = userList;
	}

	@Override
	public String toString() {
		return "UserModel{" +
				"user=" + user +
				", userList=" + userList +
				'}';
	}
}
