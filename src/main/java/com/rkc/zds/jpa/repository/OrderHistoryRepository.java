package com.rkc.zds.jpa.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.rkc.zds.jpa.entity.OrderHistoryEntity;

public interface OrderHistoryRepository extends JpaRepository<OrderHistoryEntity, Integer>, JpaSpecificationExecutor<OrderHistoryEntity> {
	 
    Optional<OrderHistoryEntity> findByHistoryId(String id);
	
    //Page<OrderHistoryEntity> findByTitleLike(Pageable pageable, String description);
	 
	//OrderHistoryEntity findByTitle(String name);
	
	//OrderHistoryEntity findByOrderHistory(String key);
	
}
