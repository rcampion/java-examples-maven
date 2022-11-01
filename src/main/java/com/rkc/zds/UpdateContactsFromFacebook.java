package com.rkc.zds;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
 
import java.io.FileReader;
import java.util.Iterator;
 
/**
 * @author Crunchify.com
 * How to Read JSON Object From File in Java?
 */
 
public class UpdateContactsFromFacebook {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader("/_/data/facebook/facebook-richardkcampion-json/friends_and_followers/friends.json"));
 
			// A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
			JSONObject jsonObject = (JSONObject) obj;
 
			// A JSON array. JSONObject supports java.util.List interface.
			JSONArray companyList = (JSONArray) jsonObject.get("friends_v2");
 
			// An iterator over a collection. Iterator takes the place of Enumeration in the Java Collections Framework.
			// Iterators differ from enumerations in two ways:
			// 1. Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.
			// 2. Method names have been improved.
			Iterator<JSONObject> iterator = companyList.iterator();
			while (iterator.hasNext()) {
				//{"name":"Eric King","timestamp":1623859076}
				
		        Object entry = iterator.next();
		          
		        // typecasting obj to JSONObject
		        JSONObject jo = (JSONObject) entry;
		          
		        // getting firstName and lastName
		        String name = (String) jo.get("name");
		        
		        String[] nameParts = name.split(" ");
		        
		        String firstName = nameParts[0];
		        
		        String lastName = "";
		        
		        for(int i=1; i < nameParts.length; i++) {
		        	
		        	lastName += nameParts[i];
		        	if(i < nameParts.length -1) {
		        		lastName += " ";
		        	}
		        	
		        }
		        
				System.out.println("firstname:"+ firstName + " lastName:"+ lastName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
