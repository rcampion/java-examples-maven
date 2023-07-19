package com.rkc.zds.jpa.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.rkc.zds.jpa.entity.OrderLineEntity;

public interface OrderLineRepository extends JpaRepository<OrderLineEntity, Integer>, JpaSpecificationExecutor<OrderLineEntity> {
	 
    Optional<OrderLineEntity> findByLineId(String id);
	
    //Page<OrderLineEntity> findByTitleLike(Pageable pageable, String description);
	 
	//OrderLineEntity findByTitle(String name);
	
	//OrderLineEntity findByOrderLine(String key);
	
}
