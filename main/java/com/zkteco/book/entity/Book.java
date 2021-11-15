package com.zkteco.book.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "bookid")	
	private Integer bookId;

	@Column(name = "ISBN")
	private String isbn;

	@Column(name = "bookname")
	private String bookName;

	@Column(name = "title")
	private String title;

	@Column(name = "language")
	private String language;

	@Column(name = "Publisher")
	private String publisher;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "publisheddate")
	private Date publishedDate = new Date();

	@Column(name = "Phone")
	private Long publisherphone;

	@Column(name = "Address")
	private String publisheraddress;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UpdateDate")
	private Date pubupdateddate = new Date();

	@Column(name = "Price")
	private int price;

	@Column(name = "Volume")
	private int volume;

	@Column(name = "AuthorID")
	private Long authorId;

	@Column(name = "AuthorName")
	private String authorName;

	@Column(name = "AuthorEmail")
	private String authoremailId;



	

}
