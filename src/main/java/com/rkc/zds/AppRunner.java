package com.rkc.zds;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.rkc.zds.jpa.entity.BookEntity;
import com.rkc.zds.jpa.entity.CustomerBookEntity;
import com.rkc.zds.jpa.entity.CustomerEntity;
import com.rkc.zds.jpa.service.BookService;
import com.rkc.zds.jpa.service.CustomerBookService;
import com.rkc.zds.jpa.service.CustomerService;

@Component
public class AppRunner implements CommandLineRunner {

	@Autowired
	BookService bookService;

	@Autowired
	CustomerService customerService;

	@Autowired
	CustomerBookService customerBookService;
	
	@PersistenceUnit(unitName = "booksEntityManager")
	private EntityManagerFactory entityManagerFactory;

	@Override
	public void run(String... args) throws Exception {
		
		populateBooks();
		
		queryBooks();
		
	}

	private void queryBooks() {
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction tx = null;
		List result;
		
        try {
        	
            tx = em.getTransaction();
            tx.begin(); 
            
			result = em.createNativeQuery(
//					"CREATE TABLE PAGE_VIEW (ID INTEGER NOT NULL,"
//					+ "		COUNT INTEGER,"
//					+ "		METHOD VARCHAR(50),"
//					+ "		PAGE VARCHAR(1000),"
//					+ "		STATUS VARCHAR(100)"
//					+ "	)").executeUpdate();            
			"SELECT CustomerBook.BookID, "
			+ "CustomerBook.CustomerID, "
			+ "Book.Author, "
			+ "Book.Title, "
			+ "Book.Category, "
			+ "Customer.Name "
			+ "FROM books.CustomerBook "
			+ "LEFT JOIN books.Book "
			+ "ON books.CustomerBook.BookID = books.Book.id "
			+ "LEFT JOIN books.Customer "
			+ "ON books.CustomerBook.CustomerID = books.Customer.id  "          
			+ "WHERE books.Book.Category = 1 or books.Book.Category = 2;"
			+ "	").getResultList();         

			
			tx.commit();
        } catch (Exception e) {
            System.out.println(e);
        }
		

	}

	private void populateBooks() {
		BookEntity book1 = new BookEntity();
		
		book1.setTitle("Clean Code");
		book1.setAuthor("Robert Cecil Martin");
		book1.setCategory(1);
		
		book1 = bookService.saveBook(book1);
		
		BookEntity book2 = new BookEntity();
		
		book2.setTitle("Code Complete");
		book2.setAuthor("Steve McConnell");
		book2.setCategory(2);
		
		book2 = bookService.saveBook(book2);
		
		BookEntity book3 = new BookEntity();
		
		book3.setTitle("Refactoring");
		book3.setAuthor("Martin Fowler, Kent Beck");
		book3.setCategory(3);
		
		book3 = bookService.saveBook(book3);
		
		CustomerEntity customer1 = new CustomerEntity();
		
		customer1.setFirstName("Richard");
		customer1.setLastName("Campion");
		
		customer1 = customerService.saveCustomer(customer1);
		
		CustomerEntity customer2 = new CustomerEntity();
		
		customer2.setFirstName("Paul");
		customer1.setLastName("Fredette");
		
		customer2 = customerService.saveCustomer(customer2);
		
		CustomerBookEntity customerBook1 = new CustomerBookEntity();
		
		customerBook1.setBookId(book1.getBookId());
		customerBook1.setCustomerId(customer1.getCustomerId());
		
		customerBookService.saveCustomerBook(customerBook1);
		
		CustomerBookEntity customerBook2 = new CustomerBookEntity();
		
		customerBook2.setBookId(book2.getBookId());
		customerBook2.setCustomerId(customer1.getCustomerId());
		
		customerBookService.saveCustomerBook(customerBook2);
		
		CustomerBookEntity customerBook3 = new CustomerBookEntity();
		
		customerBook3.setBookId(book1.getBookId());
		customerBook3.setCustomerId(customer2.getCustomerId());
		
		customerBookService.saveCustomerBook(customerBook3);
		
		CustomerBookEntity customerBook4 = new CustomerBookEntity();
		
		customerBook4.setBookId(book3.getBookId());
		customerBook4.setCustomerId(customer2.getCustomerId());
		
		customerBookService.saveCustomerBook(customerBook4);
					
	}
}
