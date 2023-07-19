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

import com.rkc.zds.jpa.entity.OrderLineEntity;
import com.rkc.zds.jpa.repository.OrderLineRepository;
import com.rkc.zds.jpa.service.OrderLineService;

@Service
public class OrderLineServiceImpl implements OrderLineService {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderLineServiceImpl.class);
	
	@Autowired
	@Qualifier("booksEntityManager")
	private EntityManagerFactory entityManagerFactory;

	@Autowired
	private OrderLineRepository orderLineRepo;
	
	@Override
	public Page<OrderLineEntity> findOrderLines(Pageable pageable) {
		return orderLineRepo.findAll(pageable);
	}

	@Override
	public OrderLineEntity getOrderLine(Integer id) {

		Optional<OrderLineEntity> addressDTO = orderLineRepo.findById(id);
		OrderLineEntity address = null;
		
		if(addressDTO.isPresent()) {
			address = addressDTO.get();
		}
		
		return address;
	}

	@Override
	public OrderLineEntity saveOrderLine(OrderLineEntity address) {

		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = null;
		
		OrderLineEntity result = null;

		try {
			tx = em.getTransaction();
			tx.begin();

			result = orderLineRepo.save(address);

			tx.commit();
		} catch (Exception e) {
			//System.out.println(e);
			logger.error(e.toString());
		}
		
		return result;
	}

	@Override
	public void updateOrderLine(OrderLineEntity address) {

		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = null;

		try {
			tx = em.getTransaction();
			tx.begin();
			
			orderLineRepo.saveAndFlush(address);

			tx.commit();
		} catch (Exception e) {
			//System.out.println(e);
			logger.error(e.toString());
		}		
	}

	@Override
	public void deleteOrderLine(int id) {
		EntityManagerFactory emf = getEntityManagerFactory();
		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = null;

		try {
			tx = em.getTransaction();
			tx.begin();

			orderLineRepo.deleteById(id);

			tx.commit();
		} catch (Exception e) {
			//System.out.println(e);
			logger.error(e.toString());
		}
		
	}

/*	
	@Override
	public OrderLineEntity findOrderLine(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Page<OrderLineEntity> searchOrderLines(String name) {
		final PageRequest pageRequest = PageRequest.of(0, 10, sortByNameASC());

		return addressRepo.findByTitleLike(pageRequest, "%" + name + "%");

	}
*/
	@Override
	public Page<OrderLineEntity> searchOrderLines(Pageable pageable, Specification<OrderLineEntity> spec) {

		return orderLineRepo.findAll(spec, pageable);

	}

	@Override
	public OrderLineEntity getOrderLine(int id) {
		Optional<OrderLineEntity> address = orderLineRepo.findById(id);

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
