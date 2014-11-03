package com.pshah.ds.wgraph;


public class Graph {

	private final int INFINITY = 1000000;
	private final int MAX_VERT = 20;
	private Vertex[] vertextList;
	private int[][] adjMat;
	private int nVerts;
	private int currVert;
	private PriorityQ thePQ;
	private int nTree;

	public Graph() {
		vertextList = new Vertex[MAX_VERT];
		adjMat = new int[MAX_VERT][MAX_VERT];
		nVerts = 0;
		
		for (int j = 0; j < MAX_VERT; j++) {
			for (int k = 0; k < MAX_VERT; k++)
				adjMat[j][k] = INFINITY;
		}
		
		thePQ = new PriorityQ();
	}

	public void addVertx(char label) {
		vertextList[nVerts++] = new Vertex(label);
	}

	public void addEdge(int source, int end, int weight) {
		adjMat[source][end] = weight;
		adjMat[end][source] = weight;
	}

	public void displayVertex(int v) {
		System.out.print(vertextList[v].label);
	}
	
	public void mstw(){
		currVert = 0;
		
		while(nTree < nVerts -1){
			
			vertextList[currVert].isInTree = true;
			nTree++;
			
			for(int j=0; j < nVerts; j++){
				
				int distance = adjMat[currVert][j];
				
				if(j == currVert || vertextList[j].isInTree || distance == INFINITY)
					continue;
				
				putinPQ(j, distance);
			}
			
			if(thePQ.size() == 0){
				System.out.println("Graph is not Connected.");
				return;
			}
			
			Edge edge = thePQ.removeMin();
			int srcVert = edge.srcVert;
			currVert = edge.destVert;
			
			System.out.print(vertextList[srcVert].label);
			System.out.print(vertextList[this.currVert].label);
			System.out.print(" ");
		}
		
		for(int j=0; j < nVerts; j++)
			vertextList[j].isInTree = false;
	}
	
	public void putinPQ(int newVert, int newDist){
		
		int queueIndex = thePQ.find(newVert);
		
		if(queueIndex != -1){
			Edge tempEdge = thePQ.peekN(queueIndex);
			int oldDist = tempEdge.distance;
			if(oldDist > newDist){
				thePQ.removeN(queueIndex);
				Edge edge = new Edge(currVert, newVert, newDist);
				thePQ.insert(edge);
			}
		}
		else{
			Edge edge = new Edge(currVert, newVert, newDist);
			thePQ.insert(edge);
		}
	}
	
	public static void main(String[] args) {
		
		Graph graph = new Graph();
		graph.addVertx('A'); //0
		graph.addVertx('B'); //1
		graph.addVertx('C'); //2
		graph.addVertx('D'); //3
		graph.addVertx('E'); //4
		graph.addVertx('F'); //5
		
		graph.addEdge(0, 1, 6); //AB 6
		graph.addEdge(0, 3, 4); //AD 4
		graph.addEdge(1, 2, 10); //BC 10
		graph.addEdge(1, 3, 7); //BD 7
		graph.addEdge(1, 4, 7); //BE 7
		graph.addEdge(2, 3, 8); //CD 8
		graph.addEdge(2, 4, 5); //CE 5
		graph.addEdge(2, 5, 6); //CF 6
		graph.addEdge(3, 4, 12); //DE 12
		graph.addEdge(4, 5, 7); //EF 7
		
		System.out.println("Minimum Spanning Tree: ");
		graph.mstw();
		System.out.println();
		
		
	}
}
