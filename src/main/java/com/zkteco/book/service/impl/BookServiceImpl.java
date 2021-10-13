package com.zkteco.book.service.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import com.zkteco.book.converter.BookConverter;
import com.zkteco.book.delete.response.ErrorCount;
import com.zkteco.book.delete.response.SuccessCount;
import com.zkteco.book.dto.BookDTO;
import com.zkteco.book.dto.ResultDTO;
import com.zkteco.book.entity.Book;
import com.zkteco.book.exception.BookNotFoundException;
import com.zkteco.book.repository.BookRepository;
import com.zkteco.book.service.BookService;

@Component
public class BookServiceImpl implements BookService {
	@Autowired
	BookRepository bookRepository;
	@Autowired
	BookService bookService;
	@Autowired
	private BookConverter bookConverter;

	@Override
	public BookDTO updateBookById(String Id, ResultDTO resultDto) throws BookNotFoundException {

		Optional<Book> book = bookRepository.findById(Id);

		if (!book.isPresent()) {
			throw new BookNotFoundException("Orders Not Available");
		}
		Book book1 = book.get();
		if (Objects.nonNull(resultDto.getIsbn()) && !"".equalsIgnoreCase(resultDto.getIsbn())) {
			book1.setIsbn(resultDto.getIsbn());
		}
		if (Objects.nonNull(resultDto.getBookName()) && !"".equalsIgnoreCase(resultDto.getBookName())) {
			book1.setBookName(resultDto.getBookName());
		}
		if (Objects.nonNull(resultDto.getTitle()) && !"".equalsIgnoreCase(resultDto.getTitle())) {
			book1.setTitle(resultDto.getTitle());
		}
		if (Objects.nonNull(resultDto.getLanguage()) && !"".equalsIgnoreCase(resultDto.getLanguage())) {
			book1.setLanguage(resultDto.getLanguage());
		}
		if (Objects.nonNull(resultDto.getPublisher()) && !"".equalsIgnoreCase(resultDto.getPublisher())) {
			book1.setPublisher(resultDto.getPublisher());
		}
		if (Objects.nonNull(resultDto.getPublishedDate())) {
			book1.setPublishedDate(resultDto.getPublishedDate());
		}

		if (Objects.nonNull(resultDto.getPublisher_phone())) {
			book1.setPublisher_phone(resultDto.getPublisher_phone());
		}

		if (Objects.nonNull(resultDto.getPublisher_address())
				&& !"".equalsIgnoreCase(resultDto.getPublisher_address())) {
			book1.setPublisher_address(resultDto.getPublisher_address());
		}

		if (Objects.nonNull(resultDto.getPub_updated_date())) {
			book1.setPub_updated_date(resultDto.getPub_updated_date());
		}

		if (Objects.nonNull(resultDto.getPrice())) {
			book1.setPrice(resultDto.getPrice());
		}
		if (Objects.nonNull(resultDto.getVolume())) {
			book1.setVolume(resultDto.getVolume());
		}
		if (Objects.nonNull(resultDto.getAuthorId())) {
			book1.setAuthorId(resultDto.getAuthorId());
		}

		if (Objects.nonNull(resultDto.getAuthorName()) && !"".equalsIgnoreCase(resultDto.getAuthorName())) {
			book1.setAuthorName(resultDto.getAuthorName());
		}

		if (Objects.nonNull(resultDto.getAuthor_emailId()) && !"".equalsIgnoreCase(resultDto.getAuthor_emailId())) {
			book1.setAuthor_emailId(resultDto.getAuthor_emailId());
		}

		Book book2 = bookRepository.save(book1);
		ResultDTO dto = bookConverter.entityToDto(book2);
		BookDTO bookDto = new BookDTO();
		bookDto.setCode("ORD-01");
		bookDto.setMessage("Order resource updated Successfully");
		bookDto.setData(dto);
		return bookDto;
	}

//	@Override
//	public BookDTO deleteBookById(String id) throws BookNotFoundException {
//
//		BookDTO bookDTO = null;
//		Optional<Book> dto = bookRepository.findByBookId(id);
//		bookDTO = new BookDTO();
//		if (dto.isPresent()) {
//			bookRepository.deleteById(id);
//			bookDTO.setMessage("Deleted Successfully");
//		} else {
//
//			bookDTO.setMessage("Id Does not Exist");
//		}
//		return bookDTO;
//	}

	@Override
	public BookDTO saveBook(ResultDTO dto) {

		try {

			Book book = bookConverter.dtoToEntity(dto);
			book = bookRepository.save(book);
			ResultDTO resultDTO = bookConverter.entityToDto(book);
			BookDTO bookDTO = new BookDTO();
			bookDTO.setCode("book001");
			bookDTO.setMessage("Books Successfully Created");
			bookDTO.setData(resultDTO);
			return bookDTO;

		} catch (NullPointerException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	@Override
	public BookDTO fetchById(String id) throws BookNotFoundException {
		Optional<Book> orElse = bookRepository.findById(id);
		if (!orElse.isPresent()) {
			throw new BookNotFoundException("Book Not Available");
		}
		Book bk = orElse.get();
		ResultDTO res = bookConverter.entityToDto(bk);
		BookDTO bookDTO = new BookDTO();
		bookDTO.setCode("Emp002");
		bookDTO.setMessage("Successfully fetched by Id");
		bookDTO.setData(res);
		return bookDTO;
	}

	@Override
	public BookDTO getAllBooks(int page, int size) {
		Pageable page1 = PageRequest.of(page, size);
		Page<Book> page2 = bookRepository.findAll(page1);
		List<Book> book = new ArrayList<Book>();
		for (Book b : page2) {
			book.add(b);
		}
		BookDTO bookDto = new BookDTO();
		bookDto.setCode("book001");
		bookDto.setMessage("succesfully fetched");
		bookDto.setData(book);
		return bookDto;
	}

	@Override
	public BookDTO deleteBulkById(String ids) throws BookNotFoundException {

		String[] str = null;
		str = ids.split(",");
		int counter = 0;
		int flag = 0;
		BookDTO bookDTO1 = new BookDTO();

		SuccessCount count = new SuccessCount();
		ErrorCount count2 = new ErrorCount();
		ArrayList<String> successCounts = new ArrayList<String>();
		List<SuccessCount> list = new ArrayList<SuccessCount>();
		List<BookDTO> errorCounts = new ArrayList<BookDTO>();

		for (String string : str) {

			if (bookRepository.existsById(string)) {
				bookRepository.deleteById(string);
				successCounts.add(string);
				counter++;
				count.setSuccessCount(counter);
				count.setSuccess("Ids :" + successCounts);

				list.add(count);
			}

			else {
				flag++;
				count2.setErrorCount(flag);
				BookDTO bookDTO = new BookDTO();
				bookDTO.setCode("Book190");
				bookDTO.setMessage("Id not found");
				bookDTO.setData(string);
				errorCounts.add(bookDTO);
				count2.setFailure(errorCounts);
			}

		}

		List<Object> objects = new ArrayList<Object>();
		objects.add(count);
		objects.add(count2);
		bookDTO1.setCode("Book9087");
		bookDTO1.setMessage("One or more objects are not processed");
		bookDTO1.setData(objects);

		return bookDTO1;
	}

}
