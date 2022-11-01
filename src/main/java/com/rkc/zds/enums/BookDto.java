package com.rkc.zds.enums;

public class BookDto implements Comparable<BookDto> {

	private String id;
	private String title;
	private String author;
	private BookCategoryEnum category;
	
	public BookDto() {
	}
	
	public BookDto(String id, String title, String author, BookCategoryEnum category) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
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
	
	public BookCategoryEnum getCategory() {
		return category;
	}

	public void setCategory(BookCategoryEnum category) {
		this.category = category;
	}

	@Override
	public String toString() {

		return this.id + " " + this.title + " " + this.author + " " + this.category;
	}

	@Override
	public int compareTo(BookDto other) {
		if(!this.title.equals(other.title))
			return this.title.compareTo(other.title);
		return 0;
	}

}
