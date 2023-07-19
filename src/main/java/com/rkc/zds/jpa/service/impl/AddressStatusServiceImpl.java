package com.rkc.zds.jpa.service.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.rkc.zds.jpa.entity.AddressStatusEntity;
import com.rkc.zds.jpa.repository.AddressStatusRepository;
import com.rkc.zds.jpa.service.AddressStatusService;

@Service
public class AddressStatusServiceImpl implements AddressStatusService {
	
	private static final Logger logger = LoggerFactory.getLogger(AddressStatusServiceImpl.class);
	
	@Autowired
	@Qualifier("booksEntityManager")
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private AddressStatusRepository addressStatusRepo;
	
	@Override
	public Page<AddressStatusEntity> findAddressStatuses(Pageable pageable) {
		return addressStatusRepo.findAll(pageable);
	}

	@Override
	public AddressStatusEntity getAddressStatus(Integer id) {

		Optional<AddressStatusEntity> addressDTO = addressStatusRepo.findById(id);
		AddressStatusEntity address = null;
		
		if(addressDTO.isPresent()) {
			address = addressDTO.get();
		}
		
		return address;
	}

	@Override
	public AddressStatusEntity saveAddressStatus(AddressStatusEntity address) {

		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = null;
		
		AddressStatusEntity result = null;

		try {
			tx = em.getTransaction();
			tx.begin();

			result = addressStatusRepo.save(address);

			tx.commit();
		} catch (Exception e) {
			//System.out.println(e);
			logger.error(e.toString());
		}
		
		return result;
	}

	@Override
	public void updateAddressStatus(AddressStatusEntity address) {

		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = null;

		try {
			tx = em.getTransaction();
			tx.begin();
			
			addressStatusRepo.saveAndFlush(address);

			tx.commit();
		} catch (Exception e) {
			//System.out.println(e);
			logger.error(e.toString());
		}		
	}

	@Override
	public void deleteAddressStatus(int id) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = null;

		try {
			tx = em.getTransaction();
			tx.begin();

			addressStatusRepo.deleteById(id);

			tx.commit();
		} catch (Exception e) {
			//System.out.println(e);
			logger.error(e.toString());
		}
		
	}
	
/*
	@Override
	public AddressStatusEntity findAddressStatus(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Page<AddressStatusEntity> searchAddressStatuss(String name) {
		final PageRequest pageRequest = PageRequest.of(0, 10, sortByNameASC());

		return addressRepo.findByTitleLike(pageRequest, "%" + name + "%");

	}
*/
	@Override
	public Page<AddressStatusEntity> searchAddressStatuses(Pageable pageable, Specification<AddressStatusEntity> spec) {

		return addressStatusRepo.findAll(spec, pageable);

	}

	@Override
	public AddressStatusEntity getAddressStatus(int id) {
		Optional<AddressStatusEntity> address = addressStatusRepo.findById(id);

		return address.get();
	}
	
	private Sort sortByNameASC() {
		return Sort.by(Sort.Direction.ASC, "status_id");
	}
	
	@Override
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
}
