package com.rkc.zds.jpa.service;

import javax.persistence.EntityManagerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.rkc.zds.jpa.entity.AddressEntity;

@Service
public interface AddressService {

    AddressEntity getAddress(Integer id);
    
	public EntityManagerFactory getEntityManagerFactory();
	
	//AddressEntity findAddress(String name);

    Page<AddressEntity> findAddresses(Pageable pageable);

    //Page<AddressEntity> searchAddresss(String name);
    
	Page<AddressEntity> searchAddresses(Pageable pageable, Specification<AddressEntity> spec);

    AddressEntity getAddress(int id);    

    //Page<AddressMemberEntity> findAddressMembers(int id); 
    
    public AddressEntity saveAddress(AddressEntity address);

    public void updateAddress(AddressEntity address);

	void deleteAddress(int id);    
}
