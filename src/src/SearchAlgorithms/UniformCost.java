package src.SearchAlgorithms;

import java.util.ArrayList;
import java.util.PriorityQueue;

import src.Comparators.NodeComparator;
import src.Data.DirectedEdge;
import src.Data.Graph;
import src.Data.Node;
import src.Data.Problem;
import src.Data.States;

public class UniformCost implements SearchAlgorithm {

	private final Node failureNode = new Node(States.Failure);
	private Node solutionNode;
	private Problem p;
	private PriorityQueue<Node> pq;
	private ArrayList<States> marked;

	public UniformCost(Problem p) {

		this.p = p;
		marked = new ArrayList<States>();
		pq = new PriorityQueue<Node>(10, new NodeComparator());
		solutionNode = uc(p.getStateSpace());

	}

	private Node uc(Graph g) {

		Node initial = new Node(p.initialState());
		pq.add(initial);

		while (true) {
			if (pq.isEmpty())
				return this.failureNode;
			Node x = pq.poll();
			if (x.goalTest())
				return x;
			marked.add(x.getState());

			for (DirectedEdge w : g.adj(x.getState().ordinal())) {
				ArrayList<DirectedEdge> tmp = new ArrayList<DirectedEdge>(x.getSol());
				tmp.add(w);
				Node y = new Node(w.to(), x.getPC() + w.weight(), tmp);

				if (!marked.contains(y.getState()) && !pq.contains(y)) {
					pq.add(y);
				}
				if (pq.removeIf((Node n) -> pq.contains(n) && n.getPC() > y.getPC()))
					pq.add(y);
			}
		}

	}

	@Override
	public void printSolution() {
		// TODO Auto-generated method stub
		System.out.println("Uniform Cost:");
		this.solutionNode.printNode();
	}

}
