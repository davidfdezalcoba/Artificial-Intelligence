package task2.Data;

import java.util.ArrayList;

import task2.Data.Action;

public class Graph {

	private final int V; // number of vertices
	private int E; // number of edges
	private ArrayList<Action>[] adj; // adjacency lists

	@SuppressWarnings("unchecked")
	public Graph(int V) {
		this.V = V;
		this.E = 0;
		adj = (ArrayList<Action>[]) new ArrayList[V];
		for (int v = 0; v < V; v++)
			adj[v] = new ArrayList<Action>();
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
	public void addEdge(Action e) {
		adj[e.from().ordinal()].add(e);
		E++;
	}

	public Iterable<Action> adj(int v) {
		return adj[v];
	}

	public Iterable<Action> edges() {
		ArrayList<Action> bag = new ArrayList<Action>();
		for (int v = 0; v < V; v++)
			for (Action e : adj[v])
				bag.add(e);
		return bag;
	}

	public Graph transpose() {
		Graph t = new Graph(V);
		for (int i = 0; i < V; i++) {
			for (Action w : adj[i]) {
				t.addEdge(new Action(w.to(), w.from(), w.weight(), w.toString()));
			}
		}
		return t;
	}

}