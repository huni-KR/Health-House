package com.ssafy.vue.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "BookDto : 책 한권의 정보", description = "책의 상세 정보를 나타낸다.")
public class BookDto {

	@ApiModelProperty(value = "고유번호", example = "111-111-1111")
	private int no;
	@ApiModelProperty(value = "저자")
	private String author;
	@ApiModelProperty(value = "책제목")
	private String title;
	@ApiModelProperty(value = "상세정보")
	private String content;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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

}
