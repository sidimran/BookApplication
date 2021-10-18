package com.zkteco.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.zkteco.book.dto.BookDTO;
import com.zkteco.book.dto.ResultDTO;
import com.zkteco.book.entity.Book;
import com.zkteco.book.repository.BookRepository;

@SpringBootTest
class BookServiceTest {

	ResultDTO result = new ResultDTO();

	@Autowired
	private BookService bookService;

	@MockBean
	private static BookRepository bookRepository;

	@BeforeEach
	void setUp() {
		
		BookDTO book = new BookDTO();
		ResultDTO rst = bookService.saveBook(book);
		assertEquals(result, rst);
	}

	@Test
	void testFetchBookById() {
		BookDTO book = new BookDTO();
		book.setBookId("111");
		book.setIsbn("113123");
		book.setBookName("dell");
		book.setTitle("Sameer");
		book.setLanguage("laptop");
		book.setPublisher("Syed");
		book.setPublisher_phone(8884189238L);
		book.setPublisher_address("RT Nagar");
		book.setAuthorId(122443222222L);
		book.setAuthorName("Sameer");
		book.setAuthor_emailId("sameer@gmail.com");

		ResultDTO rst = bookService.saveBook(book);
		assertEquals(rst.getMessage(), "Books Successfully Created");
		;

	}

	@Test
	void testFetchBookByIds() {
 
	 
		ResultDTO rst=	bookService.fetchById("96");
		
		
		 assertEquals(rst.getMessage(), "Successfully fetched by Id");;

	}
	
	@Test
	void testFetchBookByIdsIfNotExist() {
 
	 
		ResultDTO rst=	bookService.fetchById("96");
		
		
		 assertEquals(rst.getMessage(), "Book Not Available");;

	}

}
