package com.rkc.zds.jpa.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rkc.zds.jpa.CustomerBookDTO;

public interface CustomerBookRepository extends JpaRepository<CustomerBookDTO, Integer> {

	Page<CustomerBookDTO> findByCustomerId(int id, Pageable pageable);

	Page<CustomerBookDTO> findByBookId(int id, Pageable pageable);
}
