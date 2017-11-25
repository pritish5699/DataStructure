package com.pshah.ds.adjacencymatrix.graph;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Graph {

	private Vertex[] vertextList;
	private int[][] adjMat;
	private int nVerts;

	public Graph(int noOfVerts) {
		vertextList = new Vertex[noOfVerts];
		adjMat = new int[noOfVerts][noOfVerts];
		nVerts = 0;
		
		for (int j = 0; j < noOfVerts; j++) {
			for (int k = 0; k < noOfVerts; k++)
				adjMat[j][k] = 0;
		}
	}

	public void addVertx(char label) {
		vertextList[nVerts++] = new Vertex(label);
	}

	public void addEdge(int source, int end) {
		adjMat[source][end] = 1;
		adjMat[end][source] = 1;
	}

	public void displayVertex(int v) {
		System.out.print(vertextList[v].label);
	}
	
	
	public int getAdjUnvisitedVertex(int v){
		
		for(int j=0; j < nVerts; j++){
			if(adjMat[v][j] == 1 && vertextList[j].isVisited == false)
				return j;
		}
		return -1;
	}
	
	private void markVertexUnvisited(){
		for(int j=0; j < nVerts; j++)
			vertextList[j].isVisited = false;
	}
	
	public void dfs(){
		Stack<Integer> stk = new Stack<Integer>();
		markVertexUnvisited();
		vertextList[0].isVisited = true;
		displayVertex(0);
		stk.push(0);
		
		while(!stk.isEmpty()){
			
			int v = getAdjUnvisitedVertex(stk.peek());
			
			if(v == -1)
				stk.pop();
			else{
				vertextList[v].isVisited = true;
				stk.push(v);
				displayVertex(v);
			}
		}
		
		markVertexUnvisited();
	}
	
	public void bfs(){
		Queue<Integer> queue = new LinkedList<Integer>();
		markVertexUnvisited();
		displayVertex(0);
		vertextList[0].isVisited = true;
		queue.add(0);
		int v2;
		while(!queue.isEmpty()){
			int v1 = queue.remove();
			while((v2=getAdjUnvisitedVertex(v1)) != -1){
				vertextList[v2].isVisited = true;
				displayVertex(v2);
				queue.add(v2);
			}
		}
		
		markVertexUnvisited();
	}
	
	public void mst(){
		
		Stack<Integer> stk = new Stack<Integer>();
		markVertexUnvisited();
		vertextList[0].isVisited = true;
		stk.push(0);
		
		while(!stk.isEmpty()){
			int currVertex = stk.peek();
			int v = getAdjUnvisitedVertex(currVertex);
			
			if(v == -1)
				stk.pop();
			else{
				vertextList[v].isVisited = true;
				stk.push(v);
				displayVertex(currVertex);
				displayVertex(v);
				System.out.print(" ");
			}
			
		}
		markVertexUnvisited();
	}
	
	public static void main(String[] args) {
		Graph graph = new Graph(10);
		graph.addVertx('A');
		graph.addVertx('B');
		graph.addVertx('C');
		graph.addVertx('D');
		graph.addVertx('E');
		
		graph.addEdge(0, 1);
		graph.addEdge(1, 2);
		graph.addEdge(0, 3);
		graph.addEdge(3, 4);
		
		System.out.println("DFS==> Visits: ");
		graph.dfs();
		System.out.println();
		
		System.out.println("BFS==> Visits: ");
		graph.bfs();
		System.out.println();
		
		System.out.println("MST: ");
		graph.mst();
		System.out.println();
		
	}
}
