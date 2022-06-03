package com.ssafy.vue.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ReviewDto : 운동시설 리뷰에 대한 글정보")
public class ReviewDto {

	@ApiModelProperty(value = "글번호")
	private int review_no;
	@ApiModelProperty(value = "가게번호")
	private int store_no;
	@ApiModelProperty(value = "가게 이름")
	private String name;
	@ApiModelProperty(value = "사용자 닉네임")
	private String nickname;
	@ApiModelProperty(value = "글쓴이 이메일")
	private String writer_email;
	@ApiModelProperty(value = "제목")
	private String title;
	@ApiModelProperty(value = "내용")
	private String content;
	
	
	public int getReview_no() {
		return review_no;
	}
	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}
	public int getStore_no() {
		return store_no;
	}
	public void setStore_no(int store_no) {
		this.store_no = store_no;
	}
	public String getWriter_email() {
		return writer_email;
	}
	public void setWriter_email(String writer_email) {
		this.writer_email = writer_email;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "ReviewDto [review_no=" + review_no + ", store_no=" + store_no + ", name=" + name + ", nickname="
				+ nickname + ", writer_email=" + writer_email + ", title=" + title + ", content=" + content + "]";
	}
	
}
