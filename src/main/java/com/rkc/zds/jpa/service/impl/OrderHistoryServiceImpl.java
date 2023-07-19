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

import com.rkc.zds.jpa.entity.OrderHistoryEntity;
import com.rkc.zds.jpa.repository.OrderHistoryRepository;
import com.rkc.zds.jpa.service.OrderHistoryService;

@Service
public class OrderHistoryServiceImpl implements OrderHistoryService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderHistoryServiceImpl.class);
	
	@Autowired
	@Qualifier("booksEntityManager")
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private OrderHistoryRepository orderHistoryRepo;
	
	@Override
	public Page<OrderHistoryEntity> findOrderHistory(Pageable pageable) {
		return orderHistoryRepo.findAll(pageable);
	}

	@Override
	public OrderHistoryEntity getOrderHistory(Integer id) {

		Optional<OrderHistoryEntity> addressDTO = orderHistoryRepo.findById(id);
		OrderHistoryEntity address = null;
		
		if(addressDTO.isPresent()) {
			address = addressDTO.get();
		}
		
		return address;
	}

	@Override
	public OrderHistoryEntity saveOrderHistory(OrderHistoryEntity address) {

		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = null;
		
		OrderHistoryEntity result = null;

		try {
			tx = em.getTransaction();
			tx.begin();

			result = orderHistoryRepo.save(address);

			tx.commit();
		} catch (Exception e) {
			//System.out.println(e);
			logger.error(e.toString());
		}
		
		return result;
	}

	@Override
	public void updateOrderHistory(OrderHistoryEntity address) {

		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = null;

		try {
			tx = em.getTransaction();
			tx.begin();
			
			orderHistoryRepo.saveAndFlush(address);

			tx.commit();
		} catch (Exception e) {
			//System.out.println(e);
			logger.error(e.toString());
		}		
	}

	@Override
	public void deleteOrderHistory(int id) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = null;

		try {
			tx = em.getTransaction();
			tx.begin();

			orderHistoryRepo.deleteById(id);

			tx.commit();
		} catch (Exception e) {
			//System.out.println(e);
			logger.error(e.toString());
		}
		
	}

/*	
	@Override
	public OrderHistoryEntity findOrderHistory(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Page<OrderHistoryEntity> searchOrderHistorys(String name) {
		final PageRequest pageRequest = PageRequest.of(0, 10, sortByNameASC());

		return addressRepo.findByTitleLike(pageRequest, "%" + name + "%");

	}
*/
	@Override
	public Page<OrderHistoryEntity> searchOrderHistorys(Pageable pageable, Specification<OrderHistoryEntity> spec) {

		return orderHistoryRepo.findAll(spec, pageable);

	}

	@Override
	public OrderHistoryEntity getOrderHistory(int id) {
		Optional<OrderHistoryEntity> address = orderHistoryRepo.findById(id);

		return address.get();
	}
	
	private Sort sortByNameASC() {
		return Sort.by(Sort.Direction.ASC, "address_id");
	}
	
	@Override
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}
}
