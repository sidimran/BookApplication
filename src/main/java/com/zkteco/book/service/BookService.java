package com.zkteco.book.service;

import com.zkteco.book.dto.BookDTO;
import com.zkteco.book.dto.ResultDTO;
import com.zkteco.book.exception.ResourceNotFoundException;

public interface BookService {

//	public BookDTO deleteBookById(String id) throws BookNotFoundException;

	public ResultDTO saveBook(BookDTO dto);

	public ResultDTO fetchById(String id) throws ResourceNotFoundException;

	public ResultDTO getAllBooks(int page, int size);

	public ResultDTO updateBookById(String id, BookDTO bookDto) throws ResourceNotFoundException;

	public ResultDTO deleteBulkById(String ids) throws ResourceNotFoundException;

	



}
