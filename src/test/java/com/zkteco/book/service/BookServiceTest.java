package com.zkteco.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zkteco.book.dto.ResultDTO;
import com.zkteco.book.entity.Book;
import com.zkteco.book.exception.ResourceNotFoundException;

@SpringBootTest
class BookServiceTest {

	@Autowired
	private BookService bookService;

//	@MockBean
//	private static BookRepository bookRepository;

//	Optional<Book> book = Optional.of(new Book());

//	@BeforeEach
//	void setUp() throws Exception {
//
//		book = Optional.of(
//				Book.builder().bookId("9211").isbn("1231232133123").bookName("Bat").title("BT-01").language("Engliash")
//						.publisher("Syed").publisher_phone(888418923L).publisher_address("RT NAGAR").build());
//
//		Mockito.when(bookRepository.findById("9211")).thenReturn(book);
//
//	}

	ResultDTO result = new ResultDTO();

	@Test
	@Order(1)
	@DisplayName("Get Data based on valid Book Id")
	public void whenValidBookId_thenBookShouldFound() throws ResourceNotFoundException {
		String bookId = "92";

		result = bookService.fetchById(bookId);
		assertEquals(result.getMessage(), "Book fetched successfully");

	}

	@Test
	public void whenBookIdDoesNotExist() throws ResourceNotFoundException {
		String bookId = "007";
		result = bookService.fetchById(bookId);
		assertEquals(result.getMessage(), "Book not available");

//		Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
//			bookService.fetchById("343434535");
//		});
//		String expectedMessage = "book Not Available";
//		String actualMessage = exception.getMessage();
//		assertTrue(actualMessage.contains(expectedMessage));

	}

	@Test
	public void bookWithFilterSorting() {

		int page = 0, size = 5;

		result = bookService.getAllBooks(page, size);
		assertEquals(result.getMessage(), "succesfully fetched");
	}

	@Test
	public void deleteByIdNotFound() {

		String id = "9111";
		result = bookService.deleteBulkById(id);
		assertEquals(result.getMessage(), "One or more objects are not processed");
	}

}
