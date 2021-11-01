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
		dto.setBookId(book.getBookId().toString());
		dto.setIsbn(book.getIsbn());
		dto.setBookName(book.getBookName());
		dto.setTitle(book.getTitle());
		dto.setLanguage(book.getLanguage());
		dto.setPublisher(book.getPublisher());
		dto.setPublishedDate(book.getPublishedDate());
		dto.setPublisherphone(book.getPublisherphone());
		dto.setPublisheraddress(book.getPublisheraddress());
		dto.setPubupdateddate(book.getPubupdateddate());
		dto.setPrice(book.getPrice());
		dto.setVolume(book.getVolume());
		dto.setAuthorId(book.getAuthorId());
		dto.setAuthorName(book.getAuthorName());
		dto.setAuthoremailId(book.getAuthoremailId());
		return dto;

	}

	public List<BookDTO> entityToDto(List<Book> book) {

		return book.stream().map(this::entityToDto).collect(Collectors.toList());

	}

	public Book dtoToEntity(BookDTO bookDTO) {
		Book book = new Book();
	//	book.setBookId(Integer.parseInt(bookDTO.getBookId()));
		book.setIsbn(bookDTO.getIsbn());
		book.setBookName(bookDTO.getBookName());
		book.setTitle(bookDTO.getTitle());
		book.setLanguage(bookDTO.getLanguage());
		book.setPublisher(bookDTO.getPublisher());
		book.setPublishedDate(bookDTO.getPublishedDate());
		book.setPublisherphone(bookDTO.getPublisherphone());
		book.setPublisheraddress(bookDTO.getPublisheraddress());
		book.setPubupdateddate(bookDTO.getPubupdateddate());
		book.setPrice(bookDTO.getPrice());
		book.setVolume(bookDTO.getVolume());
		book.setAuthorId(bookDTO.getAuthorId());
		book.setAuthorName(bookDTO.getAuthorName());
		book.setAuthoremailId(bookDTO.getAuthoremailId());

		return book;

	}

	public List<Book> dtoToEntity(List<BookDTO> dto) {

		return dto.stream().map(this::dtoToEntity).collect(Collectors.toList());

	}

}
