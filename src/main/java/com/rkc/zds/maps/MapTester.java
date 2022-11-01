package com.rkc.zds.maps;

import java.util.HashMap;
import java.util.Map;

import com.rkc.zds.dto.BookDto;

public class MapTester {

	public static void main(String[] args) {

		MapTester app = new MapTester();

		// '1', 'Clean Code', 'Robert Cecil Martin'
		BookDto book1 = new BookDto("1", "2Clean Code", "Robert Cecil Martin", 0);

		// '2', 'Code Complete', 'Steve McConnell'
		BookDto book2 = new BookDto("2", "1Code Complete", "Steve McConnell", 0);

		// '3', 'Refactoring', 'Martin Fowler, Kent Beck'
		BookDto book3 = new BookDto("3", "3Refactoring", "Martin Fowler, Kent Beck", 0);

		Map<Integer, BookDto> hmap = new HashMap<Integer, BookDto>();

		hmap.put(9, book1);
		hmap.put(8, book2);
		hmap.put(7, book3);

		for (Map.Entry<Integer, BookDto> entry : hmap.entrySet()) {
			System.out.println(entry.getKey()+" "+ entry.getValue().toString());			
		}

	}

}
