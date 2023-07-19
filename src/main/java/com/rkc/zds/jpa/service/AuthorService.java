package com.rkc.zds.jpa.service;

import javax.persistence.EntityManagerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.rkc.zds.jpa.entity.AuthorEntity;

@Service
public interface AuthorService {

    AuthorEntity getAuthor(Integer id);
    
	public EntityManagerFactory getEntityManagerFactory();
	
	//AuthorEntity findAuthor(String name);

    Page<AuthorEntity> findAuthors(Pageable pageable);

    //Page<AuthorEntity> searchAuthors(String name);
    
	Page<AuthorEntity> searchAuthors(Pageable pageable, Specification<AuthorEntity> spec);

    AuthorEntity getAuthor(int id);    

    //Page<AuthorMemberEntity> findAuthorMembers(int id); 
    
    public AuthorEntity saveAuthor(AuthorEntity author);

    public void updateAuthor(AuthorEntity author);

	void deleteAuthor(int id);    
}
