package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ssafy.vue.dto.ReviewDto;
import com.ssafy.vue.service.ReviewService;

import io.swagger.annotations.ApiOperation;

//http://localhost:9999/vuews/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/review")
public class ReviewRestController {

	private static final Logger logger = LoggerFactory.getLogger(ReviewRestController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	ReviewService reviewService;
	
	@ApiOperation(value = "전체 리뷰를 반환", response = List.class)
	@GetMapping
	public ResponseEntity<List<ReviewDto>> listReview() {
		logger.debug("listReview - 호출");
		return new ResponseEntity<>(reviewService.list(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "리뷰번호에 맞춰서 하나 반환", response = List.class)
	@GetMapping("/{no}")
	public ResponseEntity<ReviewDto> oneReview(@PathVariable("no") int no) {
		logger.debug("Review - 호출");
		return new ResponseEntity<>(reviewService.one(no), HttpStatus.OK);
	}
	
	@ApiOperation(value = "store_no에 관련된 리뷰를 반환", response = List.class)
	@GetMapping("/detail/{storeno}")
	public ResponseEntity<List<ReviewDto>> storeReview(@PathVariable("storeno") int storeno) {
		logger.debug("storeReview - 호출");
		return new ResponseEntity<>(reviewService.list1(storeno), HttpStatus.OK);
	}

	@ApiOperation(value = "새로운 리뷰를 입력. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> createReview(@RequestBody ReviewDto reviewDto) {
		logger.debug("createReview - 호출");
		if(reviewService.create(reviewDto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호가 review_no에 해당하는 리뷰를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> modifyReview(@RequestBody ReviewDto reviewDto) {
		logger.debug("modifyReview - 호출");
		System.out.println("글 수정하자!!!"  + reviewDto.toString());
		if(reviewService.modify(reviewDto)) {
			System.out.println("글 수정 성공!!!");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호가 review_no에 해당하는 리뷰를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{review_no}")
	public ResponseEntity<String> deleteReview(@PathVariable("review_no") int review_no) {
		logger.debug("deleteReview - 호출");
		if(reviewService.delete(review_no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
}
