package com.rkc.zds.jpa.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.rkc.zds.jpa.entity.AuthorEntity;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Integer>, JpaSpecificationExecutor<AuthorEntity> {
	 
    Optional<AuthorEntity> findByAuthorId(String id);
	
    //Page<AuthorEntity> findByTitleLike(Pageable pageable, String description);
	 
	//AuthorEntity findByTitle(String name);
	
	//AuthorEntity findByAuthor(String key);
	
}
