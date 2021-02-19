package com.rkc.zds.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Book")
public class BookDTO {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id", unique = true, nullable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Size(min = 0, max = 100)
	@Column(name = "Title", nullable = true, length = 100)
	private String title;	

	@Size(min = 0, max = 100)
	@Column(name = "Author", nullable = true, length = 100)
	private String author;

	//@Size(min = 0, max = 45)
	@Column(name = "Category", nullable = true)
	private int category;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}
	
}
