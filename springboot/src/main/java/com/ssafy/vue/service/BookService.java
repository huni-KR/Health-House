package com.ssafy.vue.service;

import java.util.List;

import com.ssafy.vue.dto.BookDto;

public interface BookService {

	List<BookDto> list();

	BookDto search(int no);

	boolean create(BookDto bookDto);

	boolean modify(BookDto bookDto);

	boolean delete(int no);

}
