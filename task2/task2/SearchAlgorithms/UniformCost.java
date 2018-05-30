package task2.SearchAlgorithms;

import java.util.HashSet;
import java.util.PriorityQueue;

import task2.Comparators.NodeComparator;
import task2.Data.Action;
import task2.Data.Node;
import task2.Data.Problem;
import task2.Data.Result;
import task2.Data.Solution;
import task2.Data.States;

public class UniformCost extends SearchAlgorithm {

	private Problem problem;
	private PriorityQueue<Node> frontier;
	private HashSet<States> explored;

	public UniformCost(Problem p) {

		this.problem = p;
		this.explored = new HashSet<States>();
		this.frontier = new PriorityQueue<Node>(10, new NodeComparator());
		result = uc();

	}

	private Result uc() {

		Node initial = new Node(problem.initialState());
		frontier.add(initial);

		while (true) {

			if (frontier.isEmpty())
				return failureRes;

			Node node = frontier.poll();

			if (problem.goalTest(node.getState()))
				return new Solution(node);

			explored.add(node.getState());

			for (Action action : problem.actions(node.getState())) {

				Node child = new Node(problem, node, action);

				if (!explored.contains(child.getState()) && !frontier.contains(child))
					frontier.add(child);

				if (frontier.removeIf((Node n) -> n.equals(child) && n.getPC() > child.getPC()))
					frontier.add(child);
			}
		}
	}
}
