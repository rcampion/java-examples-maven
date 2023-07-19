package com.rkc.zds.jpa.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.rkc.zds.jpa.entity.BookLanguageEntity;

public interface BookLanguageRepository extends JpaRepository<BookLanguageEntity, Integer>, JpaSpecificationExecutor<BookLanguageEntity> {
	 
    Optional<BookLanguageEntity> findByLanguageId(String id);
	
    //Page<BookLanguageEntity> findByTitleLike(Pageable pageable, String description);
	 
	//BookLanguageEntity findByTitle(String name);
	
	//BookLanguageEntity findByBookLanguage(String key);
	
}
