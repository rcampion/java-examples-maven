package com.rkc.zds.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rkc.zds.jpa.CustomerDTO;

public interface CustomerRepository extends JpaRepository<CustomerDTO, Integer> {

    Optional<CustomerDTO> findById(String id);

}
