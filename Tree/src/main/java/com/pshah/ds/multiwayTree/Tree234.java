package com.pshah.ds.multiwayTree;

public class Tree234 {
	
	private Node root = new Node();
	
	public int find(double key){
		Node curreNode = root;
		int childNumber;
		
		while(true){
			if((childNumber = curreNode.findItem(key)) != -1)
				return childNumber;
			else if(curreNode.isLeaf())
				return -1;
			else
				curreNode = getNextChild(curreNode, key);
		}
	}
	
	public void insert(double dValue){
		
		Node currNode = root;
		DataItem item = new DataItem(dValue);
		
		while(true){
			
			if(currNode.isFull()){
				split(currNode);
				currNode = currNode.getParent();
				currNode = getNextChild(currNode, dValue);
			}
			else if(currNode.isLeaf())
				break;
			else{
				currNode = getNextChild(currNode, dValue);
			}
		}
		
		currNode.insertItem(item);
	}
	
	public void split(Node thisNode){
		
		Node parent, child2, child3;
		DataItem itemB, itemC;
		int itemIndex;
		
		itemC = thisNode.removeItem();
		itemB = thisNode.removeItem();
		
		child2 = thisNode.disconnectChild(2);
		child3 = thisNode.disconnectChild(3);
		
		Node newRight = new Node();
		
		if(thisNode == root){
			root = new Node();
			parent = root;
			root.connectChild(0, thisNode);
		}
		else{
			parent = thisNode.getParent();
		}
		
		itemIndex = parent.insertItem(itemB);
		
		int n = parent.getNumItems();
		
		for(int j= n-1; j>0; j--){
			Node temp = parent.disconnectChild(j);
			parent.connectChild(j+1, temp);
		}
		
		parent.connectChild(itemIndex+1, newRight);
		
		newRight.insertItem(itemC);
		newRight.connectChild(0, child2);
		newRight.connectChild(1, child3);
	}
	
	public Node getNextChild(Node theNode, double theValue){
		int j;
		for(j=0; j < theNode.getNumItems(); j++)
		{
			if(theValue < theNode.getDataItem(j).data)
				return theNode.getChild(j);
		}
		return theNode.getChild(j);
	}
	
	public void displayTree(){
		recDisplayTree(root, 0, 0);
	}
	
	private void recDisplayTree(Node thisNode, int level, int childNumber){
		System.out.println("level= " + level+ " childNumber= " + childNumber + " ");
		thisNode.displayNode();
		int numItems = thisNode.getNumItems();
		
		for(int j=0; j< numItems+1; j++){
			Node nextNode = thisNode.getChild(j);
			if(nextNode != null){
				recDisplayTree(nextNode, level+1, j);
			}
			else
				return;
		}
	}
}
