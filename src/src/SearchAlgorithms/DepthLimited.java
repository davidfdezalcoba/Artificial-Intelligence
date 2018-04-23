package src.SearchAlgorithms;

import java.util.ArrayList;
import java.util.Stack;

import src.Data.DirectedEdge;
import src.Data.Graph;
import src.Data.Node;
import src.Data.Problem;
import src.Data.States;

public class DepthLimited {

	private final Node cutoffNode = new Node(States.Cutoff);
	private final Node failureNode = new Node(States.Failure);
	private boolean cutoff;
	private Node solutionNode;

	public DepthLimited() {
	}

	public DepthLimited(Problem p, int limit) {

		this.cutoff = false;
		new Stack<Node>();
		new ArrayList<States>();
		this.solutionNode = dls(p.getStateSpace(), new Node(p.initialState()), limit);

	}

	private Node dls(Graph g, Node x, int limit) {

		if (x.goalTest())
			return x;

		else if (limit == 0)
			return this.cutoffNode;

		else {

			cutoff = false;

			for (DirectedEdge w : g.adj(x.getState().ordinal())) {

				// Create child node
				ArrayList<DirectedEdge> tmp = new ArrayList<DirectedEdge>(x.getSol());
				tmp.add(w);
				Node y = new Node(w.to(), x.getPC() + w.weight(), tmp);

				Node result = dls(g, y, limit - 1);
				if (result.equals(this.cutoffNode))
					cutoff = true;
				else if (!result.equals(this.failureNode))
					return result;
			}
			if (cutoff)
				return this.cutoffNode;
			else
				return this.failureNode;
		}

	}

	public void printSolution() {
		System.out.println("Depth Limited Search: ");
		this.solutionNode.printNode();
	}

	public Node getResult() {
		return this.solutionNode;
	}

}