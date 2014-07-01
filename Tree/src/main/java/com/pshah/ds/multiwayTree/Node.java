package com.pshah.ds.multiwayTree;

public class Node {
	
	private static final int ORDER = 4;
	private int numItems;
	private Node parent;
	private Node childArray[] = new Node[ORDER];
	private DataItem itemArray[] = new DataItem[ORDER - 1];
	
	public void connectChild(int childNum, Node child){
		childArray[childNum] = child;
		if(child != null){
			child.parent = this;
		}
	}
	
	public Node disconnectChild(int childNum){
		Node temp = childArray[childNum];
		childArray[childNum] = null;
		return temp;
	}
	
	public Node getChild(int childNum){
		return childArray[childNum];
	}
	
	public Node getParent(){
		return parent;
	}
	
	public boolean isLeaf(){
		return (childArray[0] == null ? true : false);
	}
	
	public boolean isFull(){
		return (numItems == (ORDER -1) ? true : false);
	}
	
	public int getNumItems(){
		return numItems;
	}
	
	public DataItem getDataItem(int index){
		return itemArray[index];
	}
	
	//Return index of an item if found, otherwise -1.
	public int findItem(double key){
		for(int i=0; i< ORDER -1; i++){
			if(itemArray[i] == null)
				break;
			else if(itemArray[i].data == key)
				return i;
		}
		return -1;
	}
	
	
	public int insertItem(DataItem item){
		numItems++;
		double newKey = item.data;
		
		for(int j= ORDER-2; j >=0; j--){
			if(itemArray[j] == null)
				continue;
			else{
				if(newKey < itemArray[j].data)
					itemArray[j+1] =itemArray[j];
				else{
					itemArray[j+1] = item;
					return j+1;
				}
			}
		}
		itemArray[0] = item;
		return 0;
	}
	
	public DataItem removeItem(){
		DataItem temp = itemArray[numItems-1];
		itemArray[numItems-1] = null;
		numItems--;
		return temp;
	}
	
	public void displayNode(){
		
		for(int i=0; i<numItems; i++){
			itemArray[i].displayItem();
		}
		System.out.println("/");
	}
}
