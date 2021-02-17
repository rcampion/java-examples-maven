package com.rkc.zds.jpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rkc.zds.jpa.BookDTO;

@Service
public interface BookService {

    Page<BookDTO> findBooks(Pageable pageable);

    BookDTO getBook(Integer id);
    
    BookDTO saveBook(BookDTO book);
    
    void updateBook(BookDTO book);

	void deleteBook(int id);
    
}
