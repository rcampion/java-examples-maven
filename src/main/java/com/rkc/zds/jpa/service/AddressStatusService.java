package com.rkc.zds.jpa.service;

import javax.persistence.EntityManagerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.rkc.zds.jpa.entity.AddressStatusEntity;

@Service
public interface AddressStatusService {

    AddressStatusEntity getAddressStatus(Integer id);
    
	public EntityManagerFactory getEntityManagerFactory();
	
	//AddressStatusEntity findAddressStatus(String name);

    Page<AddressStatusEntity> findAddressStatuses(Pageable pageable);

    //Page<AddressStatusEntity> searchAddressStatuss(String name);
    
	Page<AddressStatusEntity> searchAddressStatuses(Pageable pageable, Specification<AddressStatusEntity> spec);

    AddressStatusEntity getAddressStatus(int id);    

    //Page<AddressStatusMemberEntity> findAddressStatusMembers(int id); 
    
    public AddressStatusEntity saveAddressStatus(AddressStatusEntity addressStatus);

    public void updateAddressStatus(AddressStatusEntity addressStatus);

	void deleteAddressStatus(int id);    
}
