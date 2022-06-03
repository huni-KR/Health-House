package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.vue.dto.ReviewDto;
import com.ssafy.vue.mapper.ReviewMapper;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewMapper reviewMapper;
	
	@Override
	public List<ReviewDto> list() {
		return reviewMapper.list();
	}
	
	@Override
	public ReviewDto one(int no) {
		return reviewMapper.one(no);
	}
	
	@Override
	public List<ReviewDto> list1(int storeno) {
		return reviewMapper.list1(storeno);
	}	

	@Override
	public boolean create(ReviewDto reviewDto) {
		return reviewMapper.create(reviewDto) == 1;
	}

	@Override
	public boolean modify(ReviewDto reviewDto) {
		return reviewMapper.modify(reviewDto) == 1;
	}
	
	@Override
	public boolean delete(int reviewNo) {
		return reviewMapper.delete(reviewNo) == 1;
	}

	

}
