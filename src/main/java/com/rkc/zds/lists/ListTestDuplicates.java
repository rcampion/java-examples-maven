package com.rkc.zds.lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class ListTestDuplicates {

	int index = 0;

	public static void main(String[] args) {
		ListTestDuplicates app = new ListTestDuplicates();

		List<String> listInput = new ArrayList<String>();
		listInput.add("Cat");
		listInput.add("Dog");
		listInput.add("Mouse");
		listInput.add("Dog");
		listInput.add("Mouse");

		System.out.println("Original List");
		System.out.println(listInput);

		List<String> output = app.removeDupsWithSet(listInput);

		System.out.println("New List");
		System.out.println(output);

		System.out.println("Remove dups in place");
		System.out.println("Original List");
		System.out.println(listInput);
		
		output = app.removeDupsInPlace(listInput);
		
		System.out.println(listInput);
		System.out.println(output);

		listInput.clear();
		listInput.add("Cat");
		listInput.add("Dog");
		listInput.add("Mouse");
		listInput.add("Dog");
		listInput.add("Mouse");

		String[] arrayInput = new String[listInput.size()];

		arrayInput = listInput.toArray(arrayInput);

		System.out.println("Original String Array");
		for (String s : arrayInput)
			System.out.println(s);

		String[] arrayOutput = new String[listInput.size()];
		
		arrayOutput = app.removeDupsFromArray(arrayInput,arrayOutput);

		System.out.println("Clean String Array");
		for (String s : arrayOutput)
			if (s != null)
				System.out.println(s);

	}

	private String[] removeDupsFromArray(String[] arrayInput, String[] arrayOutput) {

		for (int i = 0; i < arrayInput.length; i++) {
			for (int j = i + 1; j < arrayInput.length; j++) {
				if (!arrayInput[i].equals(arrayInput[j])) {
					arrayOutput = addToArray(arrayOutput, arrayInput[i]);
				}
			}
		}
		return arrayOutput;
	}

	private String[] addToArray(String[] arrayOutput, String string) {
		boolean dup = false;

		for (String element : arrayOutput) {
			if (string.equals(element)) {
				dup = true;
			}
		}
		if (!dup) {
			arrayOutput[index] = string;
			index++;
		}
		return arrayOutput;
	}

	private List<String> removeDupsInPlace(List<String> list) {

		for (int i = 0; i < list.size(); i++) {
			for (int j = i + 1; j < list.size(); j++) {
				if (list.get(i).equalsIgnoreCase(list.get(j))) {
					list.remove(j);
					j--;
				}
			}
		}
		return list;
	}

	private List<String> removeDupsWithSet(List<String> input) {
		Set<String> set = new TreeSet<String>(input);

		List<String> newList = new ArrayList<String>(set);

		return newList;
	}

}
