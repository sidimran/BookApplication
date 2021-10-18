package com.zkteco.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.zkteco.book.entity.Book;
import com.zkteco.book.repository.BookRepository;

class BookServiceTest {

	@Autowired
	private BookService bookService;

	@MockBean
	private static BookRepository bookRepository;

	@BeforeEach
	void setUp() {
		Optional<Book> book = Optional.of(Book.builder().bookId("1111").isbn("122211").bookName("ABC").title("Hello")
				.publisher_phone(8884189238L).language("English").publisher("Syed").build());

		Mockito.when(bookRepository.findById("1111")).thenReturn(book);
	}

	@Test
	void testFetchBookById() {
		String bookId = "1111";
		Book book = bookRepository.getById(bookId);
		assertEquals(bookId, book.getBookId());

	}

}
