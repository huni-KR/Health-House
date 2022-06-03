package com.ssafy.vue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.vue.dto.BookDto;
import com.ssafy.vue.mapper.BookMapper;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookMapper bookMapper;
	
	@Override
	public List<BookDto> list() {
		return bookMapper.list();
	}

	@Override
	public BookDto search(int no) {
		return bookMapper.search(no);
	}

	@Override
	public boolean create(BookDto bookDto) {
		return bookMapper.create(bookDto) == 1;
	}

	@Override
	public boolean modify(BookDto bookDto) {
		return bookMapper.modify(bookDto) == 1;
	}

	@Override
	@Transactional
	public boolean delete(int no) {
		bookMapper.deleteComment(no);
		return bookMapper.delete(no) == 1;
	}

}
