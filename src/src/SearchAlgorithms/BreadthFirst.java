package src.SearchAlgorithms;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

import src.Data.DirectedEdge;
import src.Data.Graph;
import src.Data.Node;
import src.Data.Problem;
import src.Data.States;

public class BreadthFirst implements SearchAlgorithm {

	private final Node failureNode = new Node(States.Failure);
	private Node solutionNode;
	private ConcurrentLinkedQueue<Node> q;
	private ArrayList<States> marked;

	/**
	 * Takes a new problem and runs the algorithm on it.
	 * 
	 * @param p
	 */
	public BreadthFirst(Problem p) {

		this.q = new ConcurrentLinkedQueue<Node>();
		this.marked = new ArrayList<States>();
		Node initial = new Node(p.initialState());
		q.add(initial);
		this.solutionNode = bfs(p.getStateSpace());

	}

	/**
	 * The algorithm itself.
	 * 
	 * @param g
	 *            The state space of the problem
	 * @return A Node structure containing, upon success, the solution node. If no
	 *         solution was found, a Failure Node shall be returned
	 */
	private Node bfs(Graph g) {

		while (true) {

			if (q.isEmpty())
				return this.failureNode;

			Node x = q.poll();
			marked.add(x.getState());

			for (DirectedEdge w : g.adj(x.getState().ordinal())) {

				// Create child node
				ArrayList<DirectedEdge> tmp = new ArrayList<DirectedEdge>(x.getSol());
				tmp.add(w);
				Node y = new Node(w.to(), x.getPC() + w.weight(), tmp);

				if (!marked.contains(y.getState()) && !q.contains(y)) {

					if (y.goalTest())
						return y;

					q.add(y);
				}
			}
		}
	}

	public void printSolution() {
		System.out.println("Breadth First Search: ");
		this.solutionNode.printNode();
	}

}