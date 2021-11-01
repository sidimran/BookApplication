package com.zkteco.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zkteco.book.dto.BookDTO;
import com.zkteco.book.dto.ResultDTO;
import com.zkteco.book.entity.Book;
import com.zkteco.book.exception.ResourceNotFoundException;
import com.zkteco.book.repository.BookRepository;
import com.zkteco.book.service.impl.BookServiceImpl;

@RunWith(MockitoJUnitRunner.class)
class BookServiceTest  {

	@InjectMocks
	private BookService bookService= new BookServiceImpl();
	 
	@Mock
	private BookRepository bookRepository;
	ResultDTO result = new ResultDTO();
	
	
	

	@Test
	@Order(1)
	@DisplayName("Get Data based on valid Book Id")
	void testWhenValidBookId_thenBookShouldFound() throws ResourceNotFoundException {
		String bookId = "1";

		result = bookService.fetchById(bookId);
	 
		assertEquals("Book fetched successfully", result.getMessage());

	}
 
	/*
	 * @Test void testWhenBookIdDoesNotExist() throws ResourceNotFoundException {
	 * String bookId = "007"; result = bookService.fetchById(bookId);
	 * assertEquals("Book not available", result.getMessage());
	 * 
	 * }
	 * 
	 * @Test void testBookWithFilterSorting() {
	 * 
	 * int page = 0, size = 5;
	 * 
	 * result = bookService.getAllBooks(page, size);
	 * assertEquals("succesfully fetched", result.getMessage()); }
	 * 
	 * @Test void testDeleteByIdNotFound() {
	 * 
	 * String id = "9111"; result = bookService.deleteBulkById(id);
	 * assertEquals("One or more objects are not processed", result.getMessage()); }
	 * 
	 * @Test void testDeleteById() { String id = "2"; result =
	 * bookService.deleteBulkById(id);
	 * assertEquals("One or more objects are not processed", result.getMessage()); }
	 * 
	 * @Test void testSaveBook() { BookDTO dto = new BookDTO(); dto.setIsbn("1234");
	 * dto.setBookName("HelloJava"); dto.setTitle("Yello");
	 * dto.setLanguage("English"); dto.setPublisher("kavi");
	 * dto.setPublisherphone(88877877L); dto.setPublisheraddress("asdasdas");
	 * dto.setPrice(200); dto.setVolume(1); dto.setAuthorId(123L);
	 * dto.setAuthorName("Pratap"); dto.setAuthoremailId("pratap@gmail.com"); result
	 * = bookService.saveBook(dto); assertEquals("Books Successfully Created",
	 * result.getMessage()); }
	 */
}
