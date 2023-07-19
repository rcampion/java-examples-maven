package com.rkc.zds.jpa.service;

import javax.persistence.EntityManagerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.rkc.zds.jpa.entity.BookAuthorEntity;

@Service
public interface BookAuthorService {

    BookAuthorEntity getBookAuthor(Integer id);
    
	public EntityManagerFactory getEntityManagerFactory();
	
	//BookAuthorEntity findBookAuthor(String name);

    Page<BookAuthorEntity> findBookAuthors(Pageable pageable);

    //Page<BookAuthorEntity> searchBookAuthors(String name);
    
	Page<BookAuthorEntity> searchBookAuthors(Pageable pageable, Specification<BookAuthorEntity> spec);

    BookAuthorEntity getBookAuthor(int id);    

    //Page<BookAuthorMemberEntity> findBookAuthorMembers(int id); 
    
    public BookAuthorEntity saveBookAuthor(BookAuthorEntity bookAuthor);

    public void updateBookAuthor(BookAuthorEntity bookAuthor);

	void deleteBookAuthor(int id);    
}
