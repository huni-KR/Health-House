package com.ssafy.vue.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.ReviewDto;
import com.ssafy.vue.dto.SportDto;
import com.ssafy.vue.service.HouseMapService;
import com.ssafy.vue.service.SportService;

@RestController
@RequestMapping("/sport")
@CrossOrigin("*")
public class SportController {

	private final Logger logger = LoggerFactory.getLogger(SportController.class);

	@Autowired
	private SportService sportService;

	@GetMapping()
	public ResponseEntity<List<SportDto>> getSport(@RequestParam("aptCode") int aptCode,
			@RequestParam("distance") int distance) throws Exception {
		return new ResponseEntity<List<SportDto>>(sportService.getSport(aptCode, distance), HttpStatus.OK);
	}

	@GetMapping("/gym")
	public ResponseEntity<List<SportDto>> getGym(@RequestParam("aptCode") int aptCode,
			@RequestParam("distance") int distance) throws Exception {
		return new ResponseEntity<List<SportDto>>(sportService.getGym(aptCode, distance), HttpStatus.OK);
	}

	@GetMapping("/golf")
	public ResponseEntity<List<SportDto>> getGolf(@RequestParam("aptCode") int aptCode,
			@RequestParam("distance") int distance) throws Exception {
		return new ResponseEntity<List<SportDto>>(sportService.getGolf(aptCode, distance), HttpStatus.OK);
	}

	@GetMapping("/etc")
	public ResponseEntity<List<SportDto>> getEtc(@RequestParam("aptCode") int aptCode,
			@RequestParam("distance") int distance) throws Exception {
		return new ResponseEntity<List<SportDto>>(sportService.getEtc(aptCode, distance), HttpStatus.OK);
	}
	
	@GetMapping("/detail/{no}")
	public ResponseEntity<SportDto> getInfo(@PathVariable("no") int no) throws SQLException {
		logger.debug("getInfo - 호출");
		return new ResponseEntity<SportDto>(sportService.getInfo(no), HttpStatus.OK);
	}

}
