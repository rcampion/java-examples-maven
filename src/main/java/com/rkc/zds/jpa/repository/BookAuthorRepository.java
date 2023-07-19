package com.rkc.zds.jpa.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.rkc.zds.jpa.entity.BookAuthorEntity;

public interface BookAuthorRepository extends JpaRepository<BookAuthorEntity, Integer>, JpaSpecificationExecutor<BookAuthorEntity> {
	 
    Optional<BookAuthorEntity> findByBookId(String id);
	
    //Page<AuthorEntity> findByTitleLike(Pageable pageable, String description);
	 
	//AuthorEntity findByTitle(String name);
	
	//AuthorEntity findByAuthor(String key);
	
}
