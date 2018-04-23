package src.SearchAlgorithms;

import java.util.ArrayList;

import src.Data.DirectedEdge;
import src.Data.Graph;
import src.Data.Node;
import src.Data.Problem;
import src.Data.States;

public class IDAStar implements SearchAlgorithm {

	private final Node failureNode = new Node(States.Failure);
	private final Node cutoffNode = new Node(States.Cutoff);
	private int smallestCutoff;
	private Node solutionNode;;
	private boolean cutoff;
	private Problem p;

	public IDAStar(Problem p) {
		this.p = p;
		this.smallestCutoff = 530;

		this.cutoff = false;
		solutionNode = iddf();
	}

	private Node iddf() {
		Node result = this.cutoffNode;
		for (int i = 0; i < Integer.MAX_VALUE && result.getState().equals(cutoffNode.getState()); i = smallestCutoff) {
			result = (dls(p.getStateSpace(), new Node(p.initialState()), i));
		}
		return result;
	}

	private Node dls(Graph g, Node x, int limit) {

		if (x.goalTest())
			return x;

		else if (limit <= 0)
			return this.cutoffNode;

		else {

			cutoff = false;

			for (DirectedEdge w : g.adj(x.getState().ordinal())) {

				// Create child node
				ArrayList<DirectedEdge> tmp = new ArrayList<DirectedEdge>(x.getSol());
				tmp.add(w);
				Node y = new Node(w.to(), x.getPC() + w.weight(), tmp);

				Node result = dls(g, y, limit - y.getPC());
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
		System.out.println("IDAStar:");
		solutionNode.printNode();
	}
}
