package com.rkc.zds.generics;

public class GenericMethodExample {

	public static void main(String[] args) {
		GenericMethodExample app = new GenericMethodExample();
		
		Integer[] intArray= {1,2,3,4,5};
		app.printArray(intArray);
		
		Character[] charArray = {'H','E','L','L','O'};
		app.printArray(charArray);
		
	}

	private <E> void printArray(E[] array) {
		for(E element:array) {
			System.out.print(element);
		}
		System.out.println();
	}

}
