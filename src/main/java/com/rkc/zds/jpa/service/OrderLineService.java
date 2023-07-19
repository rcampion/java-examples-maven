package com.rkc.zds.jpa.service;

import javax.persistence.EntityManagerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.rkc.zds.jpa.entity.OrderLineEntity;

@Service
public interface OrderLineService {
 
	public EntityManagerFactory getEntityManagerFactory();
	
	//OrderLineEntity findOrderLine(String name);

    Page<OrderLineEntity> findOrderLines(Pageable pageable);

    //Page<OrderLineEntity> searchOrderLines(String name);
    
	Page<OrderLineEntity> searchOrderLines(Pageable pageable, Specification<OrderLineEntity> spec);

    OrderLineEntity getOrderLine(int id);
    
    OrderLineEntity getOrderLine(Integer id);

    //Page<OrderLineMemberEntity> findOrderLineMembers(int id); 
    
    public OrderLineEntity saveOrderLine(OrderLineEntity orderLine);

    public void updateOrderLine(OrderLineEntity orderLine);

	void deleteOrderLine(int id);    
}
