package com.zkteco.book.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.zkteco.book.converter.BookConverter;
import com.zkteco.book.delete.response.ErrorCount;
import com.zkteco.book.delete.response.SuccessCount;
import com.zkteco.book.dto.BookDTO;
import com.zkteco.book.dto.ResultDTO;
import com.zkteco.book.entity.Book;
import com.zkteco.book.exception.ResourceNotFoundException;
import com.zkteco.book.repository.BookRepository;
import com.zkteco.book.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BookConverter bookConverter;

	@Override
	public ResultDTO updateBookById(Integer id, BookDTO bookDto) throws ResourceNotFoundException {

		Optional<Book> book = bookRepository.findById(id);

		if (book.get().getBookId() == null) {

			ResultDTO dto = new ResultDTO();
			dto.setMessage("Book Id Not Found");
			return dto;

		}
		Book book1 = book.get();
		if (Objects.nonNull(bookDto.getIsbn()) && !"".equalsIgnoreCase(bookDto.getIsbn())) {
			book1.setIsbn(bookDto.getIsbn());
		}
		if (Objects.nonNull(bookDto.getBookName()) && !"".equalsIgnoreCase(bookDto.getBookName())) {
			book1.setBookName(bookDto.getBookName());
		}
		if (Objects.nonNull(bookDto.getTitle()) && !"".equalsIgnoreCase(bookDto.getTitle())) {
			book1.setTitle(bookDto.getTitle());
		}
		if (Objects.nonNull(bookDto.getLanguage()) && !"".equalsIgnoreCase(bookDto.getLanguage())) {
			book1.setLanguage(bookDto.getLanguage());
		}
		if (Objects.nonNull(bookDto.getPublisher()) && !"".equalsIgnoreCase(bookDto.getPublisher())) {
			book1.setPublisher(bookDto.getPublisher());
		}
		if (Objects.nonNull(bookDto.getPublishedDate())) {
			book1.setPublishedDate(bookDto.getPublishedDate());
		}

		if (Objects.nonNull(bookDto.getPublisherphone())) {
			book1.setPublisherphone(bookDto.getPublisherphone());
		}

		if (Objects.nonNull(bookDto.getPublisheraddress()) && !"".equalsIgnoreCase(bookDto.getPublisheraddress())) {
			book1.setPublisheraddress(bookDto.getPublisheraddress());
		}

		if (Objects.nonNull(bookDto.getPubupdateddate())) {
			book1.setPubupdateddate(bookDto.getPubupdateddate());
		}

		if (Objects.nonNull(bookDto.getPrice())) {
			book1.setPrice(bookDto.getPrice());
		}
		if (Objects.nonNull(bookDto.getVolume())) {
			book1.setVolume(bookDto.getVolume());
		}
		if (Objects.nonNull(bookDto.getAuthorId())) {
			book1.setAuthorId(bookDto.getAuthorId());
		}

		if (Objects.nonNull(bookDto.getAuthorName()) && !"".equalsIgnoreCase(bookDto.getAuthorName())) {
			book1.setAuthorName(bookDto.getAuthorName());
		}

		if (Objects.nonNull(bookDto.getAuthoremailId()) && !"".equalsIgnoreCase(bookDto.getAuthoremailId())) {
			book1.setAuthoremailId(bookDto.getAuthoremailId());
		}

		Book book2 = bookRepository.save(book1);
		BookDTO dto = bookConverter.entityToDto(book2);
		ResultDTO resultDto = new ResultDTO();
		resultDto.setCode("ORD-01");
		resultDto.setMessage("Book updated successfully");
		resultDto.setData(dto);
		return resultDto;
	}

	@Override
	public ResultDTO saveBook(BookDTO dto) {

		try {

			Book book = bookConverter.dtoToEntity(dto);
			book = bookRepository.save(book);
			BookDTO resultDTO = bookConverter.entityToDto(book);
			ResultDTO resultDto = new ResultDTO();
			resultDto.setCode("book001");
			resultDto.setMessage("Books Successfully Created");
			resultDto.setData(resultDTO);
			return resultDto;

		} catch (NullPointerException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	@Override
	public ResultDTO fetchById(Integer id) throws ResourceNotFoundException {
	
		Optional<Book> book = bookRepository.findById(id);
		

		if (book.get().getBookId() != null) {

			BookDTO	dto1 = bookConverter.entityToDto(book.get());

			ResultDTO result = new ResultDTO();
			result.setCode("Book-01");
			result.setMessage("Book fetched successfully");
			result.setData(dto1);
			return result;

		} else

		{
			ResultDTO result = new ResultDTO();
			result.setCode("Book-01");
			result.setMessage("Book not available");
			return result;

		}

	}

	@Override
	public ResultDTO getAllBooks(int page, int size) {

		Pageable page1 = PageRequest.of(page, size);

		Page<Book> page2 = bookRepository.findAll(page1);

		List<Book> book = new ArrayList<>();
		for (Book b : page2) {
			book.add(b);
		}
		ResultDTO resultDto = new ResultDTO();
		resultDto.setCode("book001");
		resultDto.setMessage("succesfully fetched");
		resultDto.setData(book);
		return resultDto;
	}

	@Override
	public ResultDTO deleteBulkById(String ids) {

		String[] str = null;

		str = ids.split(",");

		int counter = 0;
		int flag = 0;
		ResultDTO resultDTO = new ResultDTO();

		SuccessCount count = new SuccessCount();
		ErrorCount count2 = new ErrorCount();
		ArrayList<String> successCounts = new ArrayList<>();
		List<SuccessCount> list = new ArrayList<>();
		List<ResultDTO> errorCounts = new ArrayList<>();

		for (String string : str) {

			if (bookRepository.getById(Integer.parseInt(string.trim())) != null) {
				bookRepository.deleteById(Integer.parseInt(string));
				successCounts.add(string);
				counter++;
				count.setSuccessCount(counter);
				count.setSuccess("Ids :" + successCounts);

				list.add(count);
			}

			else {
				flag++;
				count2.setErrorCount(flag);
				resultDTO.setCode("Book190");
				resultDTO.setMessage("Id not found");
				resultDTO.setData(string);
				errorCounts.add(resultDTO);
				count2.setFailure(errorCounts);
			}

		}

		List<Object> objects = new ArrayList<>();
		objects.add(count);
		objects.add(count2);
		resultDTO.setCode("Book9087");
		resultDTO.setMessage("One or more objects are not processed");
		resultDTO.setData(objects);

		return resultDTO;
	}

}
