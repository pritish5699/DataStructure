package com.pshah.ds.heap;

public class Heap {
	
	private Node[] heaparray;
	private int maxsize;
	private int currentSize;
	
	public Heap(int max)
	{
		heaparray = new Node[max];
		currentSize = 0;
		maxsize = max;
	}
	
	public boolean isEmpty()
	{
		return currentSize==0;
	}
	
	public boolean insert(int key)
	{
		return true;
	}
	
	public Node remove()
	{
		return null;
	}
	
	public boolean change(int index, int newValue)
	{
		return true;
	}
	
	public void trickleUp(int index)
	{
		
	}
	
	public void trickleDown(int index)
	{
		
	}
	
	public void displayHeap()
	{
		
	}
}
