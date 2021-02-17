package com.rkc.zds.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

	public static void main(String[] args) {
		List<Integer> intList = Arrays.asList(5,4,3,2,1);
		List<Integer> newIntList = new ArrayList<Integer>();
		newIntList = intList.stream()
				.filter((n)->n%2==0)
				.sorted()
				.collect(Collectors.toList());
//		.collect(Collectors.groupingBy(License::getAccountId));
		
		System.out.println("Even numbers");
		System.out.println(newIntList);
	}

}
