package com.zkteco.book.repository;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.zkteco.book.entity.Book;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@RunWith(SpringRunner.class)
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
//@ContextConfiguration (locations = "classpath*:/spring/applicationContext*.xml")

@RunWith(SpringJUnit4ClassRunner.class)

@WebAppConfiguration

@ContextConfiguration(locations = {"classpath:applicationContext*.xml", "classpath:spring-servlet.xml"})
class BookRepositoryTest {

	 @Autowired
	    private BookRepository bookRepository;

	    @Autowired
	    private TestEntityManager entityManager;

	    @BeforeEach
	    void setUp() {
	    
	    	 Book book =
	                 Book.builder()
	                 .bookId("402881827c7df7ac017c7df917bb0003")
	                 .isbn("11122111")
	                 .bookName("dxccxc")
	                 .build();

	         entityManager.persist(book);
	    }

	    @Test
	    public void whenFindById_thenReturnDepartment() {
	        Book department = bookRepository.findByBookId("402881827c7df7ac017c7df917bb0003").get();
	        assertEquals(department.getBookName(), "dxccxc");
	    }
}
