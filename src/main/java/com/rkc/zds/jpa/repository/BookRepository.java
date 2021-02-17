package com.rkc.zds.jpa.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rkc.zds.jpa.BookDTO;

public interface BookRepository extends JpaRepository<BookDTO, Integer> {

    Optional<BookDTO> findById(String id);

}
