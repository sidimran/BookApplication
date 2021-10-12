package com.zkteco.book.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.zkteco.book.dto.ResultDTO;
import com.zkteco.book.entity.Book;

@Component
public class BookConverter {

	public ResultDTO entityToDto(Book book) {
		
		ResultDTO dto = new ResultDTO();
		dto.setBookId(book.getBookId());
		dto.setIsbn(book.getIsbn());
		dto.setBookName(book.getBookName());
		dto.setTitle(book.getTitle());
		dto.setLanguage(book.getLanguage());
		dto.setPublisher(book.getPublisher());
		dto.setPublishedDate(book.getPublishedDate());
		dto.setPublisher_phone(book.getPublisher_phone());
		dto.setPublisher_address(book.getPublisher_address());
		dto.setPub_updated_date(book.getPub_updated_date());
		dto.setPrice(book.getPrice());
		dto.setVolume(book.getVolume());
		dto.setAuthorId(book.getAuthorId());
		dto.setAuthorName(book.getAuthorName());
		dto.setAuthor_emailId(book.getAuthor_emailId());
		return dto;

	}

	public List<ResultDTO> entityToDto(List<Book> book) {

		return book.stream().map(x -> entityToDto(x)).collect(Collectors.toList());

	}

	public Book dtoToEntity(ResultDTO resultdto) {
		Book book = new Book();
		book.setBookId(resultdto.getBookId());
		book.setIsbn(resultdto.getIsbn());
		book.setBookName(resultdto.getBookName());
		book.setTitle(resultdto.getTitle());
		book.setLanguage(resultdto.getLanguage());
		book.setPublisher(resultdto.getPublisher());
		book.setPublishedDate(resultdto.getPublishedDate());
		book.setPublisher_phone(resultdto.getPublisher_phone());
		book.setPublisher_address(resultdto.getPublisher_address());
		book.setPub_updated_date(resultdto.getPub_updated_date());
		book.setPrice(resultdto.getPrice());
		book.setVolume(resultdto.getVolume());
		book.setAuthorId(resultdto.getAuthorId());
		book.setAuthorName(resultdto.getAuthorName());
		book.setAuthor_emailId(resultdto.getAuthor_emailId());

		return book;

	}

	public List<Book> dtoToEntity(List<ResultDTO> dto) {

		return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());

	}

}
