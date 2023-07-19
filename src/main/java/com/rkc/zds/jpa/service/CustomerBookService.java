package com.rkc.zds.jpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rkc.zds.jpa.entity.CustomerBookEntity;

@Service
public interface CustomerBookService {

    Page<CustomerBookEntity> findCustomerBooks(Pageable pageable);

    CustomerBookEntity getCustomerBook(int id);
    
    CustomerBookEntity saveCustomerBook(CustomerBookEntity customerBook);
    
    void updateCustomerBook(CustomerBookEntity customerBook);

	void deleteCustomerBook(int id);

	Page<CustomerBookEntity> findCustomerBooksByCustomerId(Pageable pageable, int id);

	Page<CustomerBookEntity> findCustomerBooksByBookId(Pageable pageable, int id);
    
}
