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
		if(currentSize >= maxsize)
			return false;
		Node node = new Node(key);
		heaparray[currentSize] = node;
		trickleUp(currentSize++);
		return true;
	}
	
	public Node remove()
	{
		Node root = heaparray[0];
		heaparray[0] = heaparray[--currentSize];
		trickleDown(0);
		return root;
	}
	
	public boolean change(int index, int newValue)
	{
		if(index < 0 || index >= currentSize)
			return false;
		int oldValue = heaparray[index].idata;
		heaparray[index].idata = newValue;
		if(oldValue < newValue)
			trickleUp(index);
		else
			trickleDown(index);
		return true;
	}
	
	public void trickleUp(int index)
	{
		Node bottom = heaparray[index];
		int parent = (index - 1) / 2;
		while(index > 0 && heaparray[parent].idata < bottom.idata)
		{
			heaparray[parent] = heaparray[index];
			index = parent;
			parent = (parent-1)/2;
		}
		heaparray[index] = bottom;
	}
	
	public void trickleDown(int index)
	{
		Node top = heaparray[index];
		int largerchild;
		while(index < currentSize/ 2)
		{
			int leftChild = 2 * index + 1;
			int rightChild = 2 * index + 2;
			if(rightChild < currentSize && heaparray[rightChild].idata > heaparray[leftChild].idata)
				largerchild = rightChild;
			else
				largerchild = leftChild;
			
			if(top.idata >= heaparray[largerchild].idata)
				break;
			heaparray[index] = heaparray[largerchild];
			index = largerchild;
		}
		heaparray[index] = top;
	}
	
	public void displayHeap()
	{
		
	}
}
