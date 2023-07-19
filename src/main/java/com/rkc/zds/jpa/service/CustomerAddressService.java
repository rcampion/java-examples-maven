package com.rkc.zds.jpa.service;

import javax.persistence.EntityManagerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.rkc.zds.jpa.entity.CustomerAddressEntity;

@Service
public interface CustomerAddressService {

	public EntityManagerFactory getEntityManagerFactory();
	
	//CustomerAddressEntity findCustomerAddress(String name);

    Page<CustomerAddressEntity> findCustomerAddresses(Pageable pageable);
    
	Page<CustomerAddressEntity> searchCustomerAddress(Pageable pageable, Specification<CustomerAddressEntity> spec);

    CustomerAddressEntity getCustomerAddress(int id);    
    
    CustomerAddressEntity getCustomerAddress(Integer id);
    
    public CustomerAddressEntity saveCustomerAddress(CustomerAddressEntity customerAddress);

    public void updateCustomerAddress(CustomerAddressEntity customerAddress);

	void deleteCustomerAddress(int id);    
}
