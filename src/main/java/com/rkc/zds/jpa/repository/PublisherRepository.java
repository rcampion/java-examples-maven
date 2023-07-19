package com.rkc.zds.jpa.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.rkc.zds.jpa.entity.PublisherEntity;

public interface PublisherRepository extends JpaRepository<PublisherEntity, Integer>, JpaSpecificationExecutor<PublisherEntity> {
	 
    Optional<PublisherEntity> findByPublisherId(String id);
	
    //Page<PublisherEntity> findByTitleLike(Pageable pageable, String description);
	 
	//PublisherEntity findByTitle(String name);
	
	//PublisherEntity findByPublisher(String key);
	
}
