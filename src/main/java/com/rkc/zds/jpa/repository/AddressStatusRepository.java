package com.rkc.zds.jpa.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.rkc.zds.jpa.entity.AddressStatusEntity;

public interface AddressStatusRepository extends JpaRepository<AddressStatusEntity, Integer>, JpaSpecificationExecutor<AddressStatusEntity> {
	 
    Optional<AddressStatusEntity> findByStatusId(String id);
	
    //Page<AddressStatusEntity> findByTitleLike(Pageable pageable, String description);
	 
	//AddressStatusEntity findByTitle(String name);
	
	//AddressStatusEntity findByAddressStatus(String key);
	
}
