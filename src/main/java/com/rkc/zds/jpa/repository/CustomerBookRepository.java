package com.rkc.zds.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.rkc.zds.jpa.entity.CustomerBookEntity;

public interface CustomerBookRepository extends JpaRepository<CustomerBookEntity, Integer>, JpaSpecificationExecutor<CustomerBookEntity> {

	Page<CustomerBookEntity> findByCustomerId(int id, Pageable pageable);

	Page<CustomerBookEntity> findByBookId(int id, Pageable pageable);

	Page<CustomerBookEntity> findByBookId(Pageable pageable, int bookId);

	List<CustomerBookEntity> findByBookId(int bookId);

}
