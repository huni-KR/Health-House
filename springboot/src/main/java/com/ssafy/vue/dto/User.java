package com.ssafy.vue.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "User (사용자정보)", description = "이메일, 이름, 비밀번호, 닉네임, 토큰")
public class User {
	@ApiModelProperty(value = "이메일이자 아이디")
	private String email;
	@ApiModelProperty(value = "이름")
	private String username;
	@ApiModelProperty(value = "비밀번호")
	private String pw;
	@ApiModelProperty(value = "닉네임")
	private String nickname;
	@ApiModelProperty(value = "토큰")
	private String token;
	public User(String email, String username, String pw, String nickname, String token) {
		super();
		this.email = email;
		this.username = username;
		this.pw = pw;
		this.nickname = nickname;
		this.token = token;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "User [email=" + email + ", username=" + username + ", pw=" + pw + ", nickname=" + nickname + ", token="
				+ token + "]";
	}
	
}
