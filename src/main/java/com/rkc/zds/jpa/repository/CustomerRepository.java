package com.rkc.zds.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.rkc.zds.jpa.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer>, JpaSpecificationExecutor<CustomerEntity> {

    Optional<CustomerEntity> findByCustomerId(String id);

}
