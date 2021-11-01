package com.zkteco.book.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;

import com.zkteco.book.dto.BookDTO;
import com.zkteco.book.dto.ResultDTO;
import com.zkteco.book.repository.BookRepository;
import com.zkteco.book.service.BookService;
import org.mockito.quality.Strictness;
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class BookControllerTest {

	@InjectMocks
	private BookController bookController;
	 

	@Mock
	private BookService bookService;
 

	@Mock
	 ResultDTO resultDTO;

	BookDTO bookDTO = new BookDTO();
 

	@Test
	void testForPostController() {
		
		bookDTO.setBookName("Dell");
		bookDTO.setTitle("Yellow");
		bookDTO.setLanguage("English");
		bookDTO.setPublisher("lulu");
		bookDTO.setPublisherphone(8887875212L);
		bookDTO.setPublisheraddress("RTNAGAR");
		bookDTO.setPrice(100);
		bookDTO.setVolume(3);
		bookDTO.setAuthorId(34455L);
		bookDTO.setAuthorName("Syed");
		bookDTO.setAuthoremailId("syed@gmail.com");

		Mockito.when(bookService.saveBook(bookDTO)).thenReturn(resultDTO);
		resultDTO = bookService.saveBook(bookDTO);
		assertNotNull(resultDTO); 

	}

	@Test
	void testForPutController() {

		Integer id = 11;
		bookDTO.setBookId(id.toString());
		bookDTO.setBookName("Dell");
		bookDTO.setTitle("Yellow");
		bookDTO.setLanguage("English");
		bookDTO.setPublisher("lulu");
		bookDTO.setPublisherphone(8887875212L);
		bookDTO.setPublisheraddress("RTNAGAR");
		bookDTO.setPrice(100);
		bookDTO.setVolume(3);
		bookDTO.setAuthorId(34455L);
		bookDTO.setAuthorName("Syed");
		bookDTO.setAuthoremailId("syed@gmail.com");
		

		Mockito.when(bookController.updateBook(id, bookDTO)).thenReturn(new ResultDTO());
 	resultDTO = bookController.updateBook(id, bookDTO);
		assertNotNull(resultDTO);

	}

	@Test
	void testForDeleteController() {

		String id = "123";
		Mockito.when(bookController.deleteBookById(id)).thenReturn(resultDTO);
		resultDTO = bookController.deleteBookById(id);
		assertNotNull(resultDTO);
	}

	@Test
	void testForGetController() {
		
		Integer id = 10;
		Mockito.when(bookController.fetchBookById(id)).thenReturn(resultDTO);
		resultDTO = bookController.fetchBookById(id);
		assertNotNull(resultDTO);

	}

}
