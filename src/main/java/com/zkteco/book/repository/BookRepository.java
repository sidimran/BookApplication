package com.zkteco.book.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.zkteco.book.entity.Book;
//import com.zkteco.book.exception.BookNotFoundException;

import com.zkteco.book.exception.ResourceNotFoundException;

public interface BookRepository extends JpaRepository<Book, String> {

	public Book findByBookNameIgnoreCase(String bookName);

	@Query(value = "Select t from Book t where t.isbn like ?1 OR t.bookName like ?1 OR t.title like ?1 OR t.language like ?1 OR t.publisher like ?1 OR t.publisherphone like ?1 OR t.publisheraddress like ?1 OR t.price like ?1 OR t.authorId like ?1 OR t.authorName like ?1 OR t.authoremailId like ?1 OR t.volume like ?1")
	Page<Book> bookContaining(String search, Pageable paging) throws ResourceNotFoundException;


}
