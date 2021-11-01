package com.zkteco.book.service;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.zkteco.book.converter.BookConverter;
import com.zkteco.book.delete.response.ErrorCount;
import com.zkteco.book.delete.response.SuccessCount;
import com.zkteco.book.dto.BookDTO;
import com.zkteco.book.dto.ResultDTO;
import com.zkteco.book.entity.Book;
import com.zkteco.book.exception.ResourceNotFoundException;
import com.zkteco.book.repository.BookRepository;
import com.zkteco.book.service.impl.BookServiceImpl;

import antlr.Utils;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

	@InjectMocks
	private BookServiceImpl bookServiceImpl;

	@Mock
	private BookRepository bookRepository;

	@Mock
	BookConverter bookConverter;

	@Mock
	private PageRequest pageRequest;

	@Mock
	private Page page;

	@Mock
	private SuccessCount SuccessCount;

	@Mock
	private ErrorCount errorCount;

	@Mock
	private Utils utils;

	ResultDTO result = new ResultDTO();

	@Test
	void testWhenValidBookId_thenBookShouldFound() throws ResourceNotFoundException {

		Integer id = 3;
		Book book = new Book();
		book.setBookId(id);
		Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(book));
		result = bookServiceImpl.fetchById(id);
		assertEquals("Book fetched successfully", result.getMessage());

	}

	@Test
	void testWhenInvalidBookId_thenBookShouldNotFound() {

		Integer id = 121;
		Book book = new Book();

		Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(book));
		result = bookServiceImpl.fetchById(id);
		assertEquals("Book not available", result.getMessage());

	}

	@Test
	void saveBookTest() {

		Book book = new Book();
		book.setIsbn("123456");
		book.setBookName("Blue");
		book.setTitle("Lenovo");
		book.setLanguage("english");
		book.setPublisher("lulu");
		book.setPublisherphone(8887875212L);
		book.setPublisheraddress("RTNAGAR");
		book.setPrice(100);
		book.setVolume(3);
		book.setAuthorId(34455L);
		book.setAuthorName("Syed");
		book.setAuthoremailId("syed@gmail.com");

		BookDTO bookDTO = new BookDTO();
		bookDTO.setIsbn("123456");
		bookDTO.setBookName("Blue");
		bookDTO.setTitle("Lenovo");
		bookDTO.setLanguage("english");
		bookDTO.setPublisher("lulu");
		bookDTO.setPublisherphone(8887875212L);
		bookDTO.setPublisheraddress("RTNAGAR");
		bookDTO.setPrice(100);
		bookDTO.setVolume(3);
		bookDTO.setAuthorId(34455L);
		bookDTO.setAuthorName("Syed");
		bookDTO.setAuthoremailId("syed@gmail.com");

		Mockito.when(bookConverter.dtoToEntity(bookDTO)).thenReturn(book);
		Mockito.when(bookRepository.save(book)).thenReturn(book);
		Mockito.when(bookConverter.entityToDto(book)).thenReturn(bookDTO);
		ResultDTO resultDTO = bookServiceImpl.saveBook(bookDTO);
		assertEquals("Books Successfully Created", resultDTO.getMessage());

	}

	@Test
	void testUpdatevalidBookId_thenUpdateBook() {

		Integer id = 3;
		Book book = new Book();

		BookDTO bookDTO = new BookDTO();

		bookDTO.setIsbn("123456");
		bookDTO.setBookName("Blue");
		bookDTO.setTitle("Lenovo");
		bookDTO.setLanguage("english");
		bookDTO.setPublisher("lulu");
		bookDTO.setPublisherphone(8887875212L);
		bookDTO.setPublisheraddress("RTNAGAR");
		bookDTO.setPrice(100);
		bookDTO.setVolume(3);
		bookDTO.setAuthorId(34455L);
		bookDTO.setAuthorName("Syed");
		bookDTO.setAuthoremailId("syed@gmail.com");

		Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(book));
		result = bookServiceImpl.updateBookById(id, bookDTO);
		assertEquals("Order resource updated Successfully", result.getMessage());

	}

	@Test
	void testUpdateWithinvalidBookId() {

		Integer id = 121;
		Book book = new Book();

		BookDTO bookDto = new BookDTO();

		Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(book));
		result = bookServiceImpl.updateBookById(id, bookDto);
		assertEquals("Book Id Not Found", result.getMessage());

	}

//	@Test
//	void testGetAllBooks_thenReturnAllBooks() {
//
//		int size = 5;
//		int page = 0;
//		Page<Book> page1 = null;
//		
//		Pageable pageable;
//
//		List<Book> tasks = new ArrayList<>();
//		Page<Book> pagedTasks = new PageImpl(tasks);
//
//		Mockito.when(pageRequest.of(page, size)).thenReturn(pageRequest);
//		
////		//when(Utils.s (pageable, anyInt(), anyInt(), anyString(), anyString())).thenReturn(pageableMock);
////
//////		Mockito.when(bookRepository.findAll()).thenReturn((List<Book>) page1);
//////		result = bookServiceImpl.getAllBooks(page, size);
//////		assertEquals("succesfully fetched", result.getMessage());
//
//	}

	@Test
	public void testGetAllBooks_thenReturnAllBooks() {
		Pageable pageable = PageRequest.of(0, 8);
		 mockStatic(Utils.class);
		 when(Utils.sort(pageable,pageRequest.of(0, 6))).thenReturn(pageable);

//	        mockStatic(Utils.class);
//	        when(Utils.sort(any(Pageable.class), anyInt(), anyInt(), anyString(), anyString())).thenReturn(pageableMock);
//	        when(bookServiceImpl.getAllBooks(0, 6)(StatusEnum.A, pageableMock)).thenReturn(employeePage);
//	    }

		Mockito.when(pageRequest.of(0, 6)).thenReturn((PageRequest) pageable);
		result = bookServiceImpl.getAllBooks(0, 6);
		assertEquals("succesfully fetched", result.getMessage());
	}

	@Test
	void testDeleteById() {

		String id = "1";
		result = bookServiceImpl.deleteBulkById(id);
		assertEquals("One or more objects are not processed", result.getMessage());
	}

}
