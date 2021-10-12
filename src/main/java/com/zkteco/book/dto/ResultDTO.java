package com.zkteco.book.dto;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultDTO {

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
	private Date publishedDate = new Date();

	@NotNull(message = "Enter Publisher Phone Number")
	private Long publisher_phone;

	@NotNull(message = "Enter Publisher Address")
	private String publisher_address;

	@NotNull(message = "Enter Published Updated_date")
	private Date pub_updated_date = new Date();

	@NotNull(message = "Enter Book Price")
	private int price;

	@NotNull(message = "Enter Book Volume")
	private int volume;

	@NotNull(message = "Enter AuthorId")
	private Long authorId;

	@NotNull(message = "Enter AuthorName")
	private String authorName;

	@NotNull(message = "Enter EmailId")
	private String author_emailId;

}
