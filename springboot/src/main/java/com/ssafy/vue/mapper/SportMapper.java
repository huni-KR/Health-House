package com.ssafy.vue.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ssafy.vue.dto.SportDto;

public interface SportMapper {

	List<SportDto> getSport(@Param("aptCode") int aptCode, @Param("distance") int distance) throws SQLException;
	List<SportDto> getGym(@Param("aptCode") int aptCode, @Param("distance") int distance) throws SQLException;
	List<SportDto> getGolf(@Param("aptCode") int aptCode, @Param("distance") int distance) throws SQLException;
	List<SportDto> getEtc(@Param("aptCode") int aptCode, @Param("distance") int distance) throws SQLException;
	SportDto getInfo(int no) throws SQLException;
	
}
