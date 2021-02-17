package com.rkc.zds.linkedlist.impl;

import com.rkc.zds.exceptions.SinglyLinkedListException;
import com.rkc.zds.generics.Node;
import com.rkc.zds.linkedlist.SinglyLinkedList;

public class SinglyLinkedListImpl<T> implements SinglyLinkedList<T> {

	Node<T> head;
	int size = 0;

	public void append(Object data) {
		if (head == null) {
			Node newNode = new Node(data);
			head = newNode;
		} else {
			Node tail = tail();
			tail.next = new Node(data);
		}
		size++;
	}

	public void remove(int index) {
		checkElementIndex(index);
		
		Node current = head;
		if (index == 0) {
			head = head.next;
		} else {
			for (int i = 0; i < index - 1; i++) {
				current = current.next;
			}
			// skip over the node
			current.next = current.next.next;
		}
		size--;

	}

	private void checkElementIndex(int index)  {
		boolean isIndex = false;
		if(index >=0 && index < size) {
			isIndex = true;			
		}
		if(isIndex == false) {
			throw new SinglyLinkedListException("Index out of bounds");
		}
	}

	private Node tail() {
		Node tail = head;
		while (tail.next != null) {
			tail = tail.next;
		}
		return tail;
	}

	public void display() {
		Node current = head;
		while (current != null) {
			System.out.print(current.data + " --> ");
			current = current.next;
		}
		System.out.println();
	}

}
