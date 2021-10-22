package com.zkteco.book.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zkteco.book.dto.BookDTO;
import com.zkteco.book.dto.ResultDTO;
import com.zkteco.book.exception.ResourceNotFoundException;
import com.zkteco.book.repository.BookRepository;
import com.zkteco.book.service.BookService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

@EnableAutoConfiguration
@RestController
@Api(value = "book", description = "Book Application")

@RequestMapping("/api/v1/book")
public class BookController {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	private BookService bookService;

	@GetMapping
	@ApiOperation(value = "Book data record by filter and sorting")
	public ResultDTO findPaginated(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) throws ResourceNotFoundException {
		return  bookService.getAllBooks(page, size);
	
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Return Book data by book Id")
	public ResultDTO fetchBookById(@PathVariable(value = "id") String id) throws ResourceNotFoundException {
		return bookService.fetchById(id);
	}

	@PostMapping
	@ApiOperation(value = "Post book data")
	public ResultDTO saveBook(@Valid @RequestBody BookDTO dto) {
		return bookService.saveBook(dto);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "update book data")
	public ResultDTO updateBook(@PathVariable(value = "id") String id, @Valid @RequestBody BookDTO bk)
			throws ResourceNotFoundException {
		return bookService.updateBookById(id, bk);
	}

	@DeleteMapping
	@ApiOperation(value = "delete data by one or more Id's")
	public ResultDTO deleteBookById(@RequestParam String id) throws ResourceNotFoundException {
		return bookService.deleteBulkById(id);
	}

}
