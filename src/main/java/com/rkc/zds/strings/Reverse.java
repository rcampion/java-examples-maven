package com.rkc.zds.strings;

public class Reverse {

	public static void main(String[] args) {
		Reverse app = new Reverse();
		
		String input = new String("ABCDE");
		
		String result = app.reverseString(input);
		
		System.out.println(result);
		
	}

	private String reverseString(String input) {
		char[] array = input.toCharArray();
		char[] output = new char[array.length];
		
		int index = 0;
		for(int i=array.length -1;i>=0;i--) {
			output[index] = array[i];
			index++;
		}
		
		return new String(output);
	}

}
