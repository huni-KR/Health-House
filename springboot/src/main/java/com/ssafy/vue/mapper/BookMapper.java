package com.ssafy.vue.mapper;

import java.util.List;

import com.ssafy.vue.dto.BookDto;

public interface BookMapper {

	List<BookDto> list();

	BookDto search(int no);

	int create(BookDto bookDto);

	int modify(BookDto bookDto);

	void deleteComment(int no);

	int delete(int no);

}
