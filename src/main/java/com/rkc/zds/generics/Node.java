package com.rkc.zds.generics;

public class Node<T> {
	
	public T data;
	public Node<T> next;
	
	public Node(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return data.toString();
	}

}
