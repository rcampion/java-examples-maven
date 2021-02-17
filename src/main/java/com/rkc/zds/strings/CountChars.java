package com.rkc.zds.strings;

import java.util.HashMap;
import java.util.Map;

public class CountChars {

	public static void main(String[] args) {

		CountChars app = new CountChars();

		String input = "A quick brown fox jumped over the lazy dog";

		Map<String, Integer> map = new HashMap<String, Integer>();

		char[] chars = input.toCharArray();

		for (char element : chars) {

			String temp = Character.toString(element);
			temp = temp.toLowerCase();

			if (!temp.equals(" ")) {

				if (map.containsKey(temp)) {

					map.put(temp, map.get(temp) + 1);

				} else {

					map.put(temp, 1);
				}
			}
		}

		for (Map.Entry<String, Integer> entry : map.entrySet())
			System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());

	}
}
