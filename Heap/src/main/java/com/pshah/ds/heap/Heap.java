package com.pshah.ds.heap;

public class Heap {

	private Node[] heaparray;
	private int maxsize;
	private int currentSize;

	public Heap(int max) {
		heaparray = new Node[max];
		currentSize = 0;
		maxsize = max;
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	public boolean insert(int key)
	{

		if(currentSize == maxsize){
			System.out.println("Heap is full");
			return false;
		}
		heaparray[currentSize] = new Node(key);
		trickleUp(currentSize++);
		return true;
	}

	public Node remove() {
		Node root = heaparray[0];
		heaparray[0] = heaparray[--currentSize];
		trickleDown(0);
		return root;
	}

	public boolean change(int index, int newVal)
	{
		if(index < 0 || index >= currentSize)
			return false;		
		int oldVal = heaparray[index].idata;
		heaparray[index].idata = newVal;
		
		if(oldVal < newVal)
			trickleUp(index);
		else
			trickleDown(index);
		return true;
	}

	public void trickleUp(int index)
	{
		int parent = (index -1) /2;
		Node bottom =heaparray[index];
		
		while(index > 0 && heaparray[parent].idata < heaparray[index].idata){
			heaparray[index] = heaparray[parent];
			index = parent;
			parent = (parent -1) /2;
		}
		heaparray[index] = bottom;
	}

	public void trickleDown(int index)
	{
		int largerChild;
		Node top = heaparray[index];
		
		while(index < currentSize/2){
			
			int left = 2*index + 1;
			int right = 2*index + 2;
			
			if(right < currentSize && heaparray[left].idata < heaparray[right].idata)
				largerChild = right;
			else
				largerChild = left;
			
			if(top.idata >= heaparray[largerChild].idata)
				break;
			
			heaparray[index] = heaparray[largerChild];
			index = largerChild;
		}
		
		heaparray[index] = top;
	}

	public void displayHeap() {

	}
}
