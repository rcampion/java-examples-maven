package com.rkc.zds.linkedlist;

import com.rkc.zds.linkedlist.impl.SinglyLinkedListImpl;

public class SinglyLinkedListTester {
	
	static SinglyLinkedList<Integer> list = new SinglyLinkedListImpl<Integer>();
	
	public static void main(String[] args) {
		SinglyLinkedListTester app = new SinglyLinkedListTester();
		
		app.appendTest();
		
		app.display();
		
		app.removeTest();
		
		app.display();
		
	}

	private void removeTest() {
		list.remove(0);
		list.remove(1);	
		//list.remove(10);
	}

	private void display() {
		list.display();		
	}

	private void appendTest() {
		list.append(1);
		list.append(2);
		list.append(3);		
	}

}
