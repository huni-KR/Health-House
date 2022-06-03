package com.ssafy.vue.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.dto.User;
import com.ssafy.vue.service.UserService;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private UserService userService;

	@ApiOperation(value = "새로운 유저 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환", response = String.class)
	@PostMapping("/regist")
	public ResponseEntity<String> registUser(@RequestBody User user) {
		logger.debug("registUser - 호출");
		if(userService.isNotDuplicated(user)) {
			if (userService.registUser(user)) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
		}
		System.out.println("중복데이터");
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "email와 pw를 입력받고 동일한 값을 가지는 유저의 정보를 반환", response = User.class)    
	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestBody User user) {
		logger.debug("loginUser - 호출");
		logger.debug("" + user);
		if(userService.loginUser(user))
			return new ResponseEntity<User>(userService.detailUser(user.getEmail()), HttpStatus.OK);
		return null;
	}
	
	@ApiOperation(value = "email와 토큰을 입력받고 동일한 값을 가지는 유저의 정보를 반환", response = User.class)    
	@PostMapping("/loginnaver")
	public ResponseEntity<User> loginUserNaver(@RequestBody User user) {
		logger.debug("loginUsernaver - 호출");
		logger.debug("" + user);
		if(userService.loginUserNaver(user))
			return new ResponseEntity<User>(userService.detailUser(user.getEmail()), HttpStatus.OK);
		return null;
	}
	
    @ApiOperation(value = "id에 따른 유저의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환", response = String.class)
	@PutMapping("/info")
	public ResponseEntity<String> updateUser(@RequestBody User user) {
		logger.debug("updateUser - 호출");
		logger.debug("" + user);
		if (userService.updateUser(user)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "id에 대당하는 사용자의 정보를 삭제, DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환", response = String.class)
	@DeleteMapping("/info/{email}")
	public ResponseEntity<String> deleteUser(@PathVariable String email) {
		logger.debug("deleteUser - 호출");
		if (userService.deleteUser(email)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	
//    @ApiOperation(value = "모든 유저의 정보를 반환한다.", response = List.class)
//	@GetMapping
//	public ResponseEntity<List<User>> retrieveUser() throws Exception {
//		logger.debug("retrieveUser - 호출");
//		return new ResponseEntity<List<User>>(userService.retrieveUser(), HttpStatus.OK);
//	}
	


    @ApiOperation(value = "email에 해당하는 유저의 정보를 반환한다.", response = User.class)    
	@GetMapping("/info/{email}")
	public ResponseEntity<User> detailUser(@PathVariable String email) {
		logger.debug("detailUser - 호출");
		return new ResponseEntity<User>(userService.detailUser(email), HttpStatus.OK);
	}
    
    @ApiOperation(value = "네이버로 가입되어있는지 확인", response = User.class)    
	@GetMapping("/loginchk/{email}")
	public ResponseEntity<String> chkUser(@PathVariable String email) {
		logger.debug("loginChk - 호출");
		if(userService.loginChk(email))
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

}