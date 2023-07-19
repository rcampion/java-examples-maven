package com.rkc.zds.jpa.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.rkc.zds.jpa.entity.OrderStatusEntity;

public interface OrderStatusRepository extends JpaRepository<OrderStatusEntity, Integer>, JpaSpecificationExecutor<OrderStatusEntity> {
	 
    Optional<OrderStatusEntity> findByStatusId(String id);
	
    //Page<OrderStatusEntity> findByTitleLike(Pageable pageable, String description);
	 
	//OrderStatusEntity findByTitle(String name);
	
	//OrderStatusEntity findByOrderStatus(String key);
	
}
