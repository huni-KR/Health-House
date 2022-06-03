package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.User;
import com.ssafy.vue.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public boolean registUser(User user) {
		return userMapper.registUser(user) == 1;
	}
	
	@Override
	public boolean isNotDuplicated(User user) {	// 등록되지 않았으면 return 값은 0일것임, true
		return userMapper.isNotDuplicated(user) == 0;
	}

	
	@Override
	public boolean loginUser(User user) {
		return userMapper.loginUser(user) == 1;
	}
	
	@Override
	public boolean loginUserNaver(User user) {
		return userMapper.loginUserNaver(user) == 1;
	}
	
	@Override
	public User detailUser(String email) {
		return userMapper.selectUserByEmail(email);
	}
	
	@Override
	public boolean updateUser(User user) {
		return userMapper.updateUser(user) == 1;
	}

	@Override
	public boolean deleteUser(String id) {
		return userMapper.deleteUser(id) == 1;
	}
	
	

	@Override
	public List<User> showUsers() {
		return userMapper.showUsers();
	}

	@Override
	public boolean loginChk(String email) {
		return userMapper.loginChk(email)==1;
	}

	



	
}