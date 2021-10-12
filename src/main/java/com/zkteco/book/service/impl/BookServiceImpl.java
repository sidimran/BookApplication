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

//	@Override
//	public BookDTO deleteBulkById(String ids) {
//
//		SuccessCount successCount = new SuccessCount();
//		ErrorCount errorCount = new ErrorCount();
//		BookDTO bookDTO = new BookDTO();
//
//		String[] str = null;
//		str = ids.split(",");
//		int counter = 0;
//		int flag = 0;
//
//		ArrayList<String> successCounts = new ArrayList<String>();
//		ArrayList<String> errorCounts = new ArrayList<String>();
//		
//		
//		List<SuccessCount> list = new ArrayList<SuccessCount>();
//        List<ErrorCount> li = new ArrayList<ErrorCount>();
//       
//        BookDTO dto = new BookDTO();
//        List<BookDTO> bkres = new ArrayList<BookDTO>();
//		for (String string : str) {
//
//			if (bookRepository.existsById(string)) {
//				bookRepository.deleteById(string);
//				successCounts.add(string);
//				counter++;
//				successCount.setSuccessCount(counter);
//				successCount.setSuccess("ids: " + successCounts);
//				list.add(successCount);
//			}
//
//			else {
//				flag++;
//				errorCount.setErrorCount(flag);
////				errorCount.setFailure(str);
////				li.add(errorCount);
//				dto.setCode("Book190");
//				dto.setMessage("Id not found");
//				errorCounts.add(string);
//				dto.setData("ids : " + string);
//				
//				
//				bkres.add(dto);
//				errorCount.setFailure(bkres);
//				li.add(errorCount);
//
//			}
//
//		}
//
//		List<Object> obj = new ArrayList<Object>();
//		obj.add(successCount);
//		obj.add(li);
//
//		bookDTO.setCode("book111");
//		bookDTO.setMessage("One or more objects are not processed");
//		bookDTO.setData(obj);
//
//		return bookDTO;
//	}

	public BookDTO deleteBulkById(String ids) throws BookNotFoundException {

		String[] strArr = null;
		strArr = ids.split(",");
		int successCount = 0;
		int errorCount = 0;
		BookDTO res = new BookDTO();

		List<SuccessCount> ct = new ArrayList<SuccessCount>();
		SuccessCount count = new SuccessCount();

		ErrorCount count1 = new ErrorCount();

		ArrayList<String> s = new ArrayList<String>();
		List<BookDTO> res2 = new ArrayList<BookDTO>();

		for (String st : strArr) {
			if (bookRepository.existsById(st)) {

				bookRepository.deleteById(st);
				s.add(st);
				successCount++;
				count.setSuccessCount(successCount);
				count.setSuccess(s);
				ct.add(count);
			}

			else {
				errorCount++;
				count1.setErrorCount(errorCount);
				BookDTO res1 = new BookDTO();
				res1.setCode("Emp005");
				res1.setMessage("id not found");
				res1.setData("id : " + st);
				res2.add(res1);
				count1.setFailure(res2);
			}
		}
		List<Object> obj = new ArrayList<Object>();
		obj.add(count);
		obj.add(count1);
		res.setCode("Emp005");
		res.setMessage("One or more objects are not processed");
		res.setData(obj);
		return res;
	}
}
