package src.SearchAlgorithms;

import src.Data.Node;
import src.Data.Problem;
import src.Data.States;

public class IterativeDeepingDF implements SearchAlgorithm {

	private final Node cutoffNode = new Node(States.Cutoff);
	private Node solutionNode;
	private Problem p;

	public IterativeDeepingDF(Problem p) {
		this.p = p;
		solutionNode = iddf();
	}

	private Node iddf() {
		Node result = this.cutoffNode;
		for (int i = 0; i < Integer.MAX_VALUE && result.getState().equals(cutoffNode.getState()); i++) {
			result = (new DepthLimited(p, i).getResult());
		}
		return result;
	}

	public void printSolution() {
		System.out.println("Iterative Deeping:");
		solutionNode.printNode();
	}
}
