package com.zkteco.book.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.zkteco.book.dto.BookDTO;
import com.zkteco.book.dto.ResultDTO;
import com.zkteco.book.entity.Book;
import com.zkteco.book.exception.BookNotFoundException;

import javassist.NotFoundException;

public interface BookService {

//	public BookDTO deleteBookById(String id) throws BookNotFoundException;

	public BookDTO saveBook(ResultDTO dto);

	public BookDTO fetchById(String id) throws BookNotFoundException;

	public BookDTO getAllBooks(int page, int size);

	BookDTO updateBookById(String Id, ResultDTO resultDto) throws BookNotFoundException;

	public BookDTO deleteBulkById(String ids) throws BookNotFoundException;

}
