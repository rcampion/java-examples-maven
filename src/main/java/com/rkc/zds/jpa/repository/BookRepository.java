package com.rkc.zds.jpa.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.rkc.zds.jpa.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity, Integer>, JpaSpecificationExecutor<BookEntity> {
	 
    Optional<BookEntity> findByBookId(Integer id);
	
    Page<BookEntity> findByTitleLike(Pageable pageable, String description);
	 
	BookEntity findByTitle(String name);
	
}
