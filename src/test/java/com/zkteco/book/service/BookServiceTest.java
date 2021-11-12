package com.zkteco.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
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

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

	@InjectMocks
	private BookServiceImpl bookServiceImpl;

	@Mock
	private BookRepository bookRepository;

	@Mock
	BookConverter bookConverter;

	@Test
	void testWhenValidBookId_thenBookShouldFound() throws ResourceNotFoundException {

		Integer id = 3;
		Book book = new Book();
		book.setBookId(id);
		Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(book));
		ResultDTO result = bookServiceImpl.fetchById(id);
		assertEquals("Book fetched successfully", result.getMessage());

	}

	@Test
	void testWhenInvalidBookId_thenBookShouldNotFound() {

		Integer id = 121;
		Book book = new Book();

		Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(book));
		ResultDTO result = bookServiceImpl.fetchById(id);
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
		book.setBookId(id);

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
		ResultDTO result = bookServiceImpl.updateBookById(id, bookDTO);
		assertEquals("Book updated successfully", result.getMessage());

	}

	@Test
	void testUpdateWithinvalidBookId() {

		Integer id = 121;
		Book book = new Book();

		BookDTO bookDto = new BookDTO();
		Mockito.when(bookRepository.findById(id)).thenReturn(Optional.of(book));
		ResultDTO result = bookServiceImpl.updateBookById(id, bookDto);
		assertEquals("Book Id Not Found", result.getMessage());

	}

	@Test
	void testGetAllBooks_thenReturnAllBooks() {
		Book book = new Book();
		book.setIsbn("123456");
		book.setBookName("123456");
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

		Pageable pageable = PageRequest.of(0, 6);
		List<Book> bookList = new ArrayList<>();
		bookList.add(book);

		Page<Book> bookpageList = new PageImpl<>(bookList);
		Mockito.when(bookRepository.findAll(pageable)).thenReturn(bookpageList);
		ResultDTO result = bookServiceImpl.getAllBooks(0, 6);
		assertEquals("succesfully fetched", result.getMessage());

	}

	@Test
	void testDeleteById() {

		String id = "1";
		ResultDTO result = bookServiceImpl.deleteBulkById(id);
		assertEquals("One or more objects are not processed", result.getMessage());
	}

}
