package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.User;

public interface UserService {
	public boolean registUser(User user);
	public boolean isNotDuplicated(User user);
	
	public boolean loginUser(User user);
	public boolean loginUserNaver(User user);
	public User detailUser(String email);
	
	public boolean updateUser(User user);
	
	public boolean deleteUser(String email);

	public boolean loginChk(String email);
	
	public List<User> showUsers();
}
