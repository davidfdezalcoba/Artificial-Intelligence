package src.Data;

import java.util.ArrayList;

import src.Data.DirectedEdge;

public class Graph {

	private final int V; // number of vertices
	private int E; // number of edges
	private ArrayList<DirectedEdge>[] adj; // adjacency lists

	@SuppressWarnings("unchecked")
	public Graph(int V) {
		this.V = V;
		this.E = 0;
		adj = (ArrayList<DirectedEdge>[]) new ArrayList[V];
		for (int v = 0; v < V; v++)
			adj[v] = new ArrayList<DirectedEdge>();
	}

	/**
	 * 
	 * @return Number of vertices of the graph
	 */
	public int V() {
		return V;
	}

	/**
	 * 
	 * @return Number of edges in the graph
	 */
	public int E() {
		return E;
	}

	/**
	 * Adds an edge to the graph and increases the number of edges.
	 * 
	 * @param e
	 *            The edge to be added
	 */
	public void addEdge(DirectedEdge e) {
		adj[e.from().ordinal()].add(e);
		E++;
	}

	public Iterable<DirectedEdge> adj(int v) {
		return adj[v];
	}

	public Iterable<DirectedEdge> edges() {
		ArrayList<DirectedEdge> bag = new ArrayList<DirectedEdge>();
		for (int v = 0; v < V; v++)
			for (DirectedEdge e : adj[v])
				bag.add(e);
		return bag;
	}

	public Graph transpose() {
		Graph t = new Graph(V);
		for (int i = 0; i < V; i++) {
			for (DirectedEdge w : adj[i]) {
				t.addEdge(new DirectedEdge(w.to(), w.from(), w.weight(), w.toString()));
			}
		}
		return t;
	}

}