package com.rkc.zds.files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TopTenWordsFromFile {

	public static void main(String[] args) throws Exception {
		String line;

		ArrayList<String> words = new ArrayList<String>();

		// Opens file in read mode
		FileReader file = new FileReader("C:\\_\\data\\files\\words.txt");
		BufferedReader br = new BufferedReader(file);

		// Reads each line
		while ((line = br.readLine()) != null) {
			String string[] = line.toLowerCase().split(" ");
			
			// Adding all words generated in previous step into words
			for (String s : string) {
				words.add(s);
			}
		}
		
		br.close();
		
		Map<String, Long> result = words.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		Map<String, Long> reverseSortedMap = new LinkedHashMap<>();
		result.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

		//print the first 10 entries
		System.out.println("Top 10 words in a file");
		int count = 1;
		for (Entry<String, Long> entry : reverseSortedMap.entrySet()) {
			System.out.println("word: " + entry.getKey() + " count: " + entry.getValue());
			
			if (count >= 10)
				break;
			
			count++;
		}
	}
}
