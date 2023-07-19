package com.rkc.zds.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.rkc.zds.jpa.entity.BookEntity;
import com.rkc.zds.jpa.entity.CustomerOrderEntity;

public interface CustomerOrderRepository extends JpaRepository<CustomerOrderEntity, Integer>, JpaSpecificationExecutor<CustomerOrderEntity> {

    Optional<CustomerOrderEntity> findByOrderId(String id);

}
