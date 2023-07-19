package com.rkc.zds.jpa.service;

import javax.persistence.EntityManagerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.rkc.zds.jpa.entity.BookEntity;

@Service
public interface BookService {
   
	public EntityManagerFactory getEntityManagerFactory();
	
	//BookEntity findBook(int name);

    Page<BookEntity> findBooks(Pageable pageable);

    Page<BookEntity> searchBooks(String name);
    
	Page<BookEntity> searchBooks(Pageable pageable, Specification<BookEntity> spec);

    BookEntity getBook(int id);
    
    BookEntity getBook(Integer id);

    //Page<BookMemberEntity> findBookMembers(int id); 
    
    public BookEntity saveBook(BookEntity book);

    public void updateBook(BookEntity book);

	void deleteBook(int id);    
}
