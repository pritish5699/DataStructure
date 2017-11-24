package com.pshah.ds.heap;

public class MaxHeap {

	private Node[] heaparray;
	private int maxsize;
	private int currentSize;

	public MaxHeap(int max) {
		heaparray = new Node[max];
		currentSize = 0;
		maxsize = max;
	}

	public static void main(String[] args) {
		MaxHeap theHeap = new MaxHeap(31);

		theHeap.insert(70);
		theHeap.insert(40);
		theHeap.insert(50);
		theHeap.insert(20);
		theHeap.insert(60);
		theHeap.insert(100);
		theHeap.insert(80);
		theHeap.insert(30);
		theHeap.insert(10);
		theHeap.insert(90);

		theHeap.displayHeap();
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	public int size() {
		return currentSize;
	}

	public boolean insert(int key) {

		if (currentSize == maxsize) {
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

	public int getTop() {
		return heaparray[0].idata;
	}

	public boolean change(int index, int newVal) {
		if (index < 0 || index >= currentSize)
			return false;
		int oldVal = heaparray[index].idata;
		heaparray[index].idata = newVal;

		if (oldVal < newVal)
			trickleUp(index);
		else
			trickleDown(index);
		return true;
	}

	public void trickleUp(int index) {
		int parent = (index - 1) / 2;
		Node bottom = heaparray[index];

		while (index > 0 && heaparray[parent].idata < bottom.idata) {
			heaparray[index] = heaparray[parent];
			index = parent;
			parent = (parent - 1) / 2;
		}
		heaparray[index] = bottom;
	}

	public void trickleDown(int index) {
		int largerChild;
		Node top = heaparray[index];

		while (index < currentSize / 2) {

			int left = 2 * index + 1;
			int right = 2 * index + 2;

			if (right < currentSize
					&& heaparray[left].idata < heaparray[right].idata)
				largerChild = right;
			else
				largerChild = left;

			if (top.idata >= heaparray[largerChild].idata)
				break;

			heaparray[index] = heaparray[largerChild];
			index = largerChild;
		}

		heaparray[index] = top;
	}

	public void displayHeap() {
		System.out.println("Max Heap Array: ");
		for (int m = 0; m < currentSize; m++)
			if (heaparray != null)
				System.out.print(heaparray[m].idata + " ");
			else
				System.out.print("-- ");
		System.out.println();

		int nBlanks = 32;
		int itemsPerRow = 1;
		int column = 0;
		int j = 0;
		String dots = "............................";
		System.out.println(dots + dots);

		while (currentSize > 0) {
			if (column == 0)
				for (int k = 0; k < nBlanks; k++)
					System.out.print(' ');
			System.out.print(heaparray[j].idata);
			if (++j == currentSize)
				break;

			if (++column == itemsPerRow) {
				nBlanks /= 2;
				itemsPerRow *= 2;
				column = 0;
				System.out.println();
			} else
				for (int k = 0; k < nBlanks * 2 - 2; k++)
					System.out.print(' ');
		}
		System.out.println("\n" + dots + dots);
	}
}
