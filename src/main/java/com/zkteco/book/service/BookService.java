package com.zkteco.book.service;

import java.util.Optional;

import com.zkteco.book.dto.BookDTO;
import com.zkteco.book.dto.ResultDTO;
import com.zkteco.book.exception.BookNotFoundException;

public interface BookService {

//	public BookDTO deleteBookById(String id) throws BookNotFoundException;

	public BookDTO saveBook(ResultDTO dto);

	public BookDTO fetchById(String id) throws BookNotFoundException;

	public BookDTO getAllBooks(int page, int size);

	BookDTO updateBookById(String Id, ResultDTO resultDto) throws BookNotFoundException;

	public BookDTO deleteBulkById(String ids) throws BookNotFoundException;

	public BookDTO saveBookTesting(ResultDTO inputBook);


}
