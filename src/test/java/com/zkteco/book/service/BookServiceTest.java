package com.zkteco.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.zkteco.book.dto.BookDTO;
import com.zkteco.book.entity.Book;
import com.zkteco.book.exception.BookNotFoundException;
import com.zkteco.book.repository.BookRepository;

@SpringBootTest
class BookServiceTest {


	private BookService bookService;

	@MockBean
	private static BookRepository bookRepository;

	@Autowired
	private TestEntityManager entityManager;

	@BeforeEach
	void setUp() {

		Optional<Book> book = Optional.of(Book.builder().bookId("402881827c7df7ac017c7df917bb0003").isbn("11122111")
				.bookName("dxccxc").title("sdasdas").language("asdasdas").publisher("sdasdas").authorName("sasdasd")
				.author_emailId("asdasd@gmail.com").build());

		entityManager.persist(book);

	}

	@Test
	@DisplayName("Get Book data based on valid bookId")
	public void whenFindById_thenReturnBook() throws BookNotFoundException {
	
		Book book = bookRepository.findByBookId("402881827c7df7ac017c7df917bb0003").get();
		assertEquals(book.getBookName(),"dxccxc");
	}

}
