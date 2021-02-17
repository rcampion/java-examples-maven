package com.rkc.zds.lambdas;

import java.util.Arrays;
import java.util.List;

public class LambdaExample {
	public static void main(String[]args) {
		
		List<Integer> intList = Arrays.asList(1,2,3,4,5);
		
		intList.forEach(n->System.out.println(n));
		
		List<String> stringList = Arrays.asList("Cat","Dog","Mouse");
	
		stringList.forEach(s->System.out.println(s));
	}
}
