package com.zkteco.book.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {

	private String bookId;
	@NotNull(message = "Enter Book ISBN")
	private String isbn;

	@NotNull(message = "Enter Book Number")
	private String bookName;

	@NotNull(message = "Enter Book Title")
	private String title;

	@NotNull(message = "Enter Book Language")
	private String language;

	@NotNull(message = "Enter Publisher")
	private String publisher;

	@NotNull(message = "Enter Published Date")
	private  Date  publishedDate = new Date();

	@NotNull(message = "Enter Publisher Phone Number")
	private Long publisherphone;

	@NotNull(message = "Enter Publisher Address")
	private String publisheraddress;

	@NotNull(message = "Enter Published Updated_date")
	private Date pubupdateddate = new Date();

	@NotNull(message = "Enter Book Price")
	private int price;

	@NotNull(message = "Enter Book Volume")
	private int volume;

	@NotNull(message = "Enter AuthorId")
	private Long authorId;

	@NotNull(message = "Enter AuthorName")
	private String authorName;

	@NotNull(message = "Enter EmailId")
	private String authoremailId;

}
