package com.rkc.zds.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookTester {

	public static void main(String[] args) {
		//'1', 'Clean Code', 'Robert Cecil Martin'
		BookDto book1 = new BookDto("1","2Clean Code","Robert Cecil Martin", 1);
		
		//'2', 'Code Complete', 'Steve McConnell'
		BookDto book2 = new BookDto("2","1Code Complete","Steve McConnell", 2);
		
		//'3', 'Refactoring', 'Martin Fowler, Kent Beck'
		BookDto book3 = new BookDto("3","3Refactoring","Martin Fowler, Kent Beck", 1);

		List<BookDto> list = new ArrayList<BookDto>();
		
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
