package com.ssafy.vue.mapper;

import java.util.List;

import com.ssafy.vue.dto.CommentDto;

public interface CommentMapper {

	List<CommentDto> list(int no);

	int create(CommentDto commentDto);

	int modify(CommentDto commentDto);

	int delete(int commentNo);
	
}
