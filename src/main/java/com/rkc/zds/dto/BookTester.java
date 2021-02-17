package com.rkc.zds.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookTester {

	public static void main(String[] args) {
		//'1', 'Clean Code', 'Robert Cecil Martin'
		Book book1 = new Book("1","2Clean Code","Robert Cecil Martin");
		
		//'2', 'Code Complete', 'Steve McConnell'
		Book book2 = new Book("2","1Code Complete","Steve McConnell");
		
		//'3', 'Refactoring', 'Martin Fowler, Kent Beck'
		Book book3 = new Book("3","3Refactoring","Martin Fowler, Kent Beck");

		List<Book> list = new ArrayList<Book>();
		
		list.add(book1);
		list.add(book2);
		list.add(book3);
		
		System.out.println("original list");
		System.out.println(list);
		
		Collections.sort(list);
		
		System.out.println("sorted list");
		System.out.println(list);		
		
	}

}
