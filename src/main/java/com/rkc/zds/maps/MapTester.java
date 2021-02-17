package com.rkc.zds.maps;

import java.util.HashMap;
import java.util.Map;

import com.rkc.zds.dto.Book;

public class MapTester {

	public static void main(String[] args) {

		MapTester app = new MapTester();

		// '1', 'Clean Code', 'Robert Cecil Martin'
		Book book1 = new Book("1", "2Clean Code", "Robert Cecil Martin");

		// '2', 'Code Complete', 'Steve McConnell'
		Book book2 = new Book("2", "1Code Complete", "Steve McConnell");

		// '3', 'Refactoring', 'Martin Fowler, Kent Beck'
		Book book3 = new Book("3", "3Refactoring", "Martin Fowler, Kent Beck");

		Map<Integer, Book> hmap = new HashMap<Integer, Book>();

		hmap.put(9, book1);
		hmap.put(8, book2);
		hmap.put(7, book3);

		for (Map.Entry<Integer, Book> entry : hmap.entrySet()) {
			System.out.println(entry.getKey()+" "+ entry.getValue().toString());			
		}

	}

}
