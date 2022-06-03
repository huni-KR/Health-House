package com.ssafy.vue.mapper;

import java.util.List;

import com.ssafy.vue.dto.CommentDto;
import com.ssafy.vue.dto.ReviewDto;

public interface ReviewMapper {

	List<ReviewDto> list();
	
	ReviewDto one(int no);
	
	List<ReviewDto> list1(int storeno);

	int create(ReviewDto reviewDto);

	int modify(ReviewDto reviewDto);

	int delete(int reviewNo);
	
}
