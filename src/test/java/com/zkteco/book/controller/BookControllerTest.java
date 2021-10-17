package com.zkteco.book.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.zkteco.book.dto.BookDTO;
import com.zkteco.book.dto.ResultDTO;
import com.zkteco.book.entity.Book;
import com.zkteco.book.service.BookService;

@WebMvcTest(BookController.class)
class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookService BookService;

	@Autowired
	private Book book;

	@BeforeEach
	void setUp() throws Exception {

		Optional<Book> book = Optional.of(Book.builder().bookId("402881827c7df7ac017c7df917bb0003").isbn("11122111")
				.bookName("dxccxc").title("sdasdas").language("asdasdas").publisher("sdasdas").authorName("sasdasd")
				.author_emailId("asdasd@gmail.com").build());

	}

	@Test
	void saveBook() {

		Optional<BookDTO> book = Optional.empty();

	}

}
