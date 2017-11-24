package com.pshah.ds.wgraph;

public class PriorityQ {
	private final int SIZE = 20;
	private Edge[] queArr;
	private int size;
	
	public PriorityQ(){
		queArr = new Edge[SIZE];
		size = 0;
	}
	
	public void insert(Edge item){
		int j;
		
		for(j=0; j< size; j++)
			if(item.distance >= queArr[j].distance)
				break;
		
		for(int k= size -1; k >=j; k--)
			queArr[k+1] = queArr[k];
		
		
		queArr[j] = item;
		size++;
	}	
	
	public Edge removeMin(){
		return queArr[--size];
	}
	
	public void removeN(int n){
		for(int i = n; i < size-1; i++)
			queArr[i] = queArr[i+1];
		size--;
	}
	
	public Edge peekMin(){
		return queArr[size-1];
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return (size==0);
	}
	
	public Edge peekN(int n){
		return queArr[n];
	}
	
	public int find(int finddex){
		for(int j=0; j < size; j++){
			if(queArr[j].destVert == finddex)
				return j;
		}
		return -1;
	}
}
