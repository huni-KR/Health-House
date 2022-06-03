package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.ReviewDto;

public interface ReviewService {

	List<ReviewDto> list();
	
	ReviewDto one(int no);	
	
	List<ReviewDto> list1(int storeno);	

	boolean create(ReviewDto reviewDto);

	boolean modify(ReviewDto reviewDto);

	boolean delete(int reviewNo);
	
}
