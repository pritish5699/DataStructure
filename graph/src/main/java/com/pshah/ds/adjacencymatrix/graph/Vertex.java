package com.pshah.ds.adjacencymatrix.graph;

public class Vertex {
	public char label;
	public boolean isVisited;

	public Vertex(char label) {
		this.label = label;
		isVisited = false;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Vertex))
			return false;

		Vertex _obj = (Vertex) obj;
		return _obj.label == label;
	}

	@Override
	public int hashCode() {
		return label;
	}

}
