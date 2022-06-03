package com.ssafy.vue.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.vue.dto.SportDto;

public interface SportService {
	List<SportDto> getSport(int aptCode, int distance) throws SQLException;
	List<SportDto> getGym(int aptCode, int distance) throws SQLException;
	List<SportDto> getGolf(int aptCode, int distance) throws SQLException;
	List<SportDto> getEtc(int aptCode, int distance) throws SQLException;	
	SportDto getInfo(int no) throws SQLException;
}
