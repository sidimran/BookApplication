package com.zkteco.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zkteco.book.dto.BookDTO;
import com.zkteco.book.dto.ResultDTO;
import com.zkteco.book.entity.Book;
import com.zkteco.book.exception.ResourceNotFoundException;

@SpringBootTest
class BookServiceTest {

	@Autowired
	private BookService bookService;

	ResultDTO result = new ResultDTO();

	@Test
	@Order(1)
	@DisplayName("Get Data based on valid Book Id")
	public void testWhenValidBookId_thenBookShouldFound() throws ResourceNotFoundException {
		String bookId = "1";

		result = bookService.fetchById(bookId);
		assertEquals(result.getMessage(), "Book fetched successfully");

	}

	@Test
	public void testWhenBookIdDoesNotExist() throws ResourceNotFoundException {
		String bookId = "007";
		result = bookService.fetchById(bookId);
		assertEquals(result.getMessage(), "Book not available");

	}

	@Test
	public void testBookWithFilterSorting() {

		int page = 0, size = 5;

		result = bookService.getAllBooks(page, size);
		assertEquals(result.getMessage(), "succesfully fetched");
	}

	@Test
	public void testDeleteByIdNotFound() {

		String id = "9111";
		result = bookService.deleteBulkById(id);
		assertEquals(result.getMessage(), "One or more objects are not processed");
	}

	@Test
	public void testDeleteById() {
		String id = "3";
		result = bookService.deleteBulkById(id);
		assertEquals(result.getMessage(), "One or more objects are not processed");
	}

	@Test
	public void testSaveBook() {
		BookDTO dto = new BookDTO();
		dto.setIsbn("123");
		dto.setBookName("HelloJava");
		dto.setTitle("Yello");
		dto.setLanguage("English");
		dto.setPublisher("kavi");
		dto.setPublisher_phone(88877877L);
		dto.setPublisher_address("asdasdas");
		dto.setPrice(200);
		dto.setVolume(1);
		dto.setAuthorId(123L);
		dto.setAuthorName("Pratap");
		dto.setAuthor_emailId("pratap@gmail.com");
		result = bookService.saveBook(dto);
		assertEquals(result.getMessage(), "Books Successfully Created");
	}
}
