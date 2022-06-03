package com.ssafy.vue.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.vue.dto.User;
@Mapper
public interface UserMapper {
	public int registUser(User user);
	public int isNotDuplicated(User user);
	
	public int loginUser(User user);
	public int loginUserNaver(User user);
	public User selectUserByEmail(String email);
	
	public int updateUser(User user);
	
	public int deleteUser(String email);
	
	public List<User> showUsers();
	public int loginChk(String email);
	
	
	
}