package com.zkteco.book.converter;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.zkteco.book.dto.BookDTO;
import com.zkteco.book.dto.ResultDTO;
import com.zkteco.book.entity.Book;

@Component
public class BookConverter {

	public BookDTO entityToDto(Book book) {
		
		BookDTO dto = new BookDTO();
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

	public List<BookDTO> entityToDto(List<Book> book) {

		return book.stream().map(x -> entityToDto(x)).collect(Collectors.toList());

	}

	public Book dtoToEntity(BookDTO bookDTO) {
		Book book = new Book();
		book.setBookId(bookDTO.getBookId());
		book.setIsbn(bookDTO.getIsbn());
		book.setBookName(bookDTO.getBookName());
		book.setTitle(bookDTO.getTitle());
		book.setLanguage(bookDTO.getLanguage());
		book.setPublisher(bookDTO.getPublisher());
		book.setPublishedDate(bookDTO.getPublishedDate());
		book.setPublisher_phone(bookDTO.getPublisher_phone());
		book.setPublisher_address(bookDTO.getPublisher_address());
		book.setPub_updated_date(bookDTO.getPub_updated_date());
		book.setPrice(bookDTO.getPrice());
		book.setVolume(bookDTO.getVolume());
		book.setAuthorId(bookDTO.getAuthorId());
		book.setAuthorName(bookDTO.getAuthorName());
		book.setAuthor_emailId(bookDTO.getAuthor_emailId());

		return book;

	}

	public List<Book> dtoToEntity(List<BookDTO> dto) {

		return dto.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());

	}

}
