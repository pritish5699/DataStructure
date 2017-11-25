package com.pshah.ds.adjacencylist.graph;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

public class Graph {

	public Graph() {
		vertices = new HashSet<>();
		edges = new HashSet<>();
		adjList = new HashMap<>();
	}

	public boolean addVertex(int label) {
		return vertices.add(new Vertex(label));
	}

	public boolean addVertex(final Vertex v) {
		return vertices.add(v);
	}

	public boolean addVertices(final Collection<Vertex> vertices) {
		return this.vertices.addAll(vertices);
	}

	public boolean removeVertex(int label) {
		return vertices.remove(new Vertex(label));
	}

	public boolean removeVertex(final Vertex v) {
		return vertices.remove(v);
	}

	public boolean addEdge(Edge e) {
		if (!edges.add(e))
			return false;

		adjList.putIfAbsent(e.v1, new HashSet<>());
		adjList.putIfAbsent(e.v2, new HashSet<>());

		adjList.get(e.v1).add(e);
		adjList.get(e.v2).add(e);

		return true;
	}

	public boolean addEdge(int vertexLabel1, int vertexLabel2) {
		return addEdge(new Edge(new Vertex(vertexLabel1), new Vertex(vertexLabel2)));
	}

	public boolean removeEdge(Edge e) {
		if (!edges.remove(e))
			return false;
		Set<Edge> edgesOfV1 = adjList.get(e.v1);
		Set<Edge> edgesOfV2 = adjList.get(e.v2);

		if (edgesOfV1 != null)
			edgesOfV1.remove(e);
		if (edgesOfV2 != null)
			edgesOfV2.remove(e);

		return true;
	}

	public boolean removeEdge(int vertexLabel1, int vertexLabel2) {
		return removeEdge(new Edge(new Vertex(vertexLabel1), new Vertex(vertexLabel2)));
	}

	public Set<Vertex> getAdjVertices(Vertex v) {
		return adjList.get(v).stream().map(e -> e.v1.equals(v) ? e.v2 : e.v1).collect(Collectors.toSet());
	}

	public Set<Vertex> getVertices() {
		return Collections.unmodifiableSet(vertices);
	}

	public Set<Edge> getEdges() {
		return Collections.unmodifiableSet(edges);
	}

	public Map<Vertex, Set<Edge>> getAdjList() {
		return Collections.unmodifiableMap(adjList);
	}

	public void bfs(final Vertex v) {
		boolean[] isVisited = new boolean[vertices.size()];
		final Queue<Vertex> queue = new LinkedList<>();
		isVisited[v.label] = true;
		queue.add(v);

		while (!queue.isEmpty()) {
			final Vertex tmp = queue.poll();
			System.out.print(tmp.label + " ");
			for (final Edge e : adjList.get(tmp)) {
				if (!isVisited[e.v2.label]) {
					isVisited[e.v2.label] = true;
					queue.offer(e.v2);
				}
			}
		}
	}

	public void dfs(final Vertex v) {
		boolean[] isVisited = new boolean[vertices.size()];
		dfsUtil(v, isVisited);
	}

	private void dfsUtil(final Vertex v, boolean[] isVisited) {
		System.out.print(v.label + " ");
		isVisited[v.label] = true;
		for (final Edge e : adjList.get(v)) {
			if (!isVisited[e.v2.label]) {
				dfsUtil(e.v2, isVisited);
			}
		}
	}

	private Set<Vertex> vertices;
	private Set<Edge> edges;
	private Map<Vertex, Set<Edge>> adjList;

	public static void main(String args[]) {
		final Graph g = new Graph();
		final Vertex v0 = new Vertex(0);
		final Vertex v1 = new Vertex(1);
		final Vertex v2 = new Vertex(2);
		final Vertex v3 = new Vertex(3);

		g.addVertex(v0);
		g.addVertex(v1);
		g.addVertex(v2);
		g.addVertex(v3);

		final Edge e01 = new Edge(v0, v1);
		final Edge e02 = new Edge(v0, v2);
		final Edge e12 = new Edge(v1, v2);
		final Edge e20 = new Edge(v2, v0);
		final Edge e23 = new Edge(v2, v3);
		final Edge e33 = new Edge(v3, v3);

		g.addEdge(e01);
		g.addEdge(e02);
		g.addEdge(e12);
		g.addEdge(e20);
		g.addEdge(e23);
		g.addEdge(e33);

		System.out.println("Following is Breadth First Traversal " + "(starting from vertex 2)");
		g.bfs(v2);
		System.out.println();
		System.out.println("Following is Depth First Traversal " + "(starting from vertex 3)");
		g.dfs(v3);
	}

}
