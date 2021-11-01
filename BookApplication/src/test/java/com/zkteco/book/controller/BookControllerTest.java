package com.zkteco.book.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.zkteco.book.dto.BookDTO;
import com.zkteco.book.dto.ResultDTO;
import com.zkteco.book.entity.Book;
import com.zkteco.book.repository.BookRepository;
import com.zkteco.book.service.BookService;

//@WebMvcTest(BookController.class)
//@EnableAutoConfiguration
//@ContextConfiguration(classes = {BookController.class})
//@SpringBootTest


@RunWith(SpringRunner.class)
class BookControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private BookService bookService;

	private Book book;
	
	BookDTO bookdto;

	ResultDTO result = new ResultDTO();

	@BeforeEach
	void setUpBeforeClass() throws Exception {

		bookdto = BookDTO.builder().bookName("Mechanical Engineering").language("Dell").price(100).build();

	}

	@Test
	void testSaveBook() throws Exception {

		BookDTO inputbook = BookDTO.builder().bookName("Mechanical Engineering").language("Dell").price(100).build();
		result.setData(inputbook);

		Mockito.when(bookService.saveBook(inputbook)).thenReturn((ResultDTO) result);

		mockMvc.perform(post("/api/v1/book").contentType(MediaType.APPLICATION_JSON)
				.content("  {\r\n" + "    \"bookName\":\"Heasdasllo\",\r\n"
						+ "    \"language\":\"Englasxasdasdish\",\r\n" + "    \"price\":100  \r\n" + " }"))
				.andExpect(status().isOk());

	}

}
