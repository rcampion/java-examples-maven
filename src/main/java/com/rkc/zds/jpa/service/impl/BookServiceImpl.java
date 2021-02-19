package com.rkc.zds.jpa.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rkc.zds.jpa.BookDTO;
import com.rkc.zds.jpa.repository.BookRepository;
import com.rkc.zds.jpa.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepo;

	@Override
	public Page<BookDTO> findBooks(Pageable pageable) {
		return bookRepo.findAll(pageable);
	}

	@Override
	public BookDTO getBook(Integer id) {

		Optional<BookDTO> bookDTO = bookRepo.findById(id);
		BookDTO book = null;
		
		if(bookDTO.isPresent()) {
			book = bookDTO.get();
		}
		
		return book;
	}

	@Override
	public BookDTO saveBook(BookDTO book) {
		return bookRepo.save(book);
	}

	@Override
	public void updateBook(BookDTO book) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteBook(int id) {
		// TODO Auto-generated method stub
		
	}

}
