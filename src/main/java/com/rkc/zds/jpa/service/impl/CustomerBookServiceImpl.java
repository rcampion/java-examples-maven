package com.rkc.zds.jpa.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rkc.zds.jpa.CustomerBookDTO;
import com.rkc.zds.jpa.repository.CustomerBookRepository;
import com.rkc.zds.jpa.service.CustomerBookService;

@Service
public class CustomerBookServiceImpl implements CustomerBookService {
	
	@Autowired
	private CustomerBookRepository customerBookRepo;

	@Override
	public Page<CustomerBookDTO> findCustomerBooks(Pageable pageable) {
		return customerBookRepo.findAll(pageable);
	}

	@Override
	public Page<CustomerBookDTO> findCustomerBooksByCustomerId(Pageable pageable, int id) {
		return customerBookRepo.findByCustomerId(id, pageable);
	}

	@Override
	public Page<CustomerBookDTO> findCustomerBooksByBookId(Pageable pageable, int id) {
		return customerBookRepo.findByBookId(id, pageable);
	}
	
	@Override
	public CustomerBookDTO getCustomerBook(int id) {
		return customerBookRepo.getOne(id);
	}

	@Override
	public CustomerBookDTO saveCustomerBook(CustomerBookDTO customerBook) {
		return customerBookRepo.save(customerBook);
	}

	@Override
	public void updateCustomerBook(CustomerBookDTO customerBook) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCustomerBook(int id) {
		// TODO Auto-generated method stub
		
	}
}
