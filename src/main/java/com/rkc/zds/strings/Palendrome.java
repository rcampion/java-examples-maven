package com.rkc.zds.strings;

public class Palendrome {

	public static void main(String[] args) {
		Palendrome app = new Palendrome();
		
		String input = "ROTXR";
		
		boolean result = app.isPalendrome(input);
		
		System.out.println(input + " isPalendrome "+ result);

	}

	private boolean isPalendrome(String input) {
		char[] array = input.toCharArray();
		
		int j = array.length -1;
		
		for(int i =0; i < array.length/2; i++) {
			
			if(array[i]!=array[j]) {
				return false;				
			}
			j--;
		}
		return true;
	}

}
