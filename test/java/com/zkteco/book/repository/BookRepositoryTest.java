package com.zkteco.book.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.zkteco.book.entity.Book;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BookRepositoryTest {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private TestEntityManager entityManager;

	@BeforeEach
	void setUp() {
		Book book = Book.builder().bookName("Mechanical Engineering").language("Dell").price(100).build();

		entityManager.persist(book);

	}

	@Test
	void whenFindById_thenReturnBook() {

		Book book = bookRepository.findByBookNameIgnoreCase("Mechanical Engineering");
		assertEquals("Mechanical Engineering", book.getBookName());

	}

}
