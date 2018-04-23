package src.SearchAlgorithms;

import java.util.ArrayList;
import java.util.Stack;

import src.Data.DirectedEdge;
import src.Data.Graph;
import src.Data.Node;
import src.Data.Problem;
import src.Data.States;

public class DepthFirst implements SearchAlgorithm {

	private Node solutionNode;
	private ArrayList<States> marked;
	private boolean haveSol;

	/**
	 * This constructor takes the problem to be solved and runs dfs on it.
	 * 
	 * @param p
	 *            The problem to be solved.
	 */
	public DepthFirst(Problem p) {

		haveSol = false;
		new Stack<Node>();
		this.marked = new ArrayList<States>();
		this.solutionNode = (dfs(p.getStateSpace(), new Node(p.initialState())));

	}

	/**
	 * The algorithm itself. Being a recursive algorithm it needs the current node
	 * expanding as a parameter.
	 * 
	 * @param g
	 *            the state space of the problem
	 * @param x
	 *            the node expanding
	 * @return node with the solution upon success, null otherwise.
	 */
	private Node dfs(Graph g, Node x) {

		Node sol = null;

		marked.add(x.getState());

		for (DirectedEdge w : g.adj(x.getState().ordinal())) {

			if (!haveSol) {
				// Create child node
				ArrayList<DirectedEdge> tmp = new ArrayList<DirectedEdge>(x.getSol());
				tmp.add(w);
				Node y = new Node(w.to(), x.getPC() + w.weight(), tmp);

				if (!marked.contains(y.getState())) {

					if (y.goalTest()) {
						haveSol = true;
						return y;
					}

					sol = dfs(g, y);
				}
			}
		}

		return sol;

	}

	@Override
	public void printSolution() {
		// TODO Auto-generated method stub
		System.out.println("Depth First:");
		this.solutionNode.printNode();
	}

}