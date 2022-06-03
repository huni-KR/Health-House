package com.ssafy.vue.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.SportDto;
import com.ssafy.vue.mapper.SportMapper;

@Service
public class SportServiceImpl implements SportService {
	
	@Autowired
	private SportMapper sportMapper;

	@Override
	public List<SportDto> getSport(int aptCode, int distance) throws SQLException {
		return sportMapper.getSport(aptCode,  distance);
	}

	@Override
	public List<SportDto> getGym(int aptCode, int distance) throws SQLException {
		return sportMapper.getGym(aptCode,  distance);
	}

	@Override
	public List<SportDto> getGolf(int aptCode, int distance) throws SQLException {
		return sportMapper.getGolf(aptCode,  distance);
	}

	@Override
	public List<SportDto> getEtc(int aptCode, int distance) throws SQLException {
		return sportMapper.getEtc(aptCode,  distance);
	}

	@Override
	public SportDto getInfo(int no) throws SQLException {
		return sportMapper.getInfo(no);
	}

}
