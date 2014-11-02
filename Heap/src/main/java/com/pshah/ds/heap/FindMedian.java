package com.pshah.ds.heap;

public class FindMedian {

	public static void main(String[] args) {

		FindMedian obj = new FindMedian();
		Integer[] arr = { 5, 15, 1, 3, 2};
		int m = 0;
		MaxHeap left = new MaxHeap(arr.length / 2 + 1);
		MinHeap right = new MinHeap(arr.length / 2 + 1);

		for (int i = 0; i < arr.length; i++) {
			m = obj.getMedian(arr[i], m, left, right);
			System.out.println("Median: "+ m);
		}

	}

	int getMedian(int e, int m, MaxHeap left, MinHeap right) {

		int diff = left.size() - right.size();

		if (diff > 0) {
			if (e < m) // current element fits in left (max) heap
			{
				// Remore top element from left heap and
				// insert into right heap
				right.insert(left.remove().idata);

				// current element fits in left (max) heap
				left.insert(e);
			} else {
				// current element fits in right (min) heap
				right.insert(e);
			}

			// Both heaps are balanced
			m = Average(left.getTop(), right.getTop());
		} else if (diff == 0) {

			if (e < m) // current element fits in left (max) heap
			{
				left.insert(e);
				m = left.getTop();
			} else {
				// current element fits in right (min) heap
				right.insert(e);
				m = right.getTop();
			}

		} else {
			if (e < m) // current element fits in left (max) heap
			{
				left.insert(e);
			} else {
				// Remove top element from right heap and
				// insert into left heap
				left.insert(right.remove().idata);

				// current element fits in right (min) heap
				right.insert(e);
			}

			// Both heaps are balanced
			m = Average(left.getTop(), right.getTop());
		}

		return m;
	}

	int Average(int a, int b) {
		return (a + b) / 2;
	}

}
