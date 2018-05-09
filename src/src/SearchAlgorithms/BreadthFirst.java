package src.SearchAlgorithms;

import java.util.HashSet;
import java.util.concurrent.ConcurrentLinkedQueue;

import src.Data.Action;
import src.Data.Node;
import src.Data.Problem;
import src.Data.Result;
import src.Data.Solution;
import src.Data.States;

public class BreadthFirst extends SearchAlgorithm {

	private Problem problem;
	private ConcurrentLinkedQueue<Node> frontier;
	private HashSet<States> explored;

	/**
	 * Takes a new problem and runs the algorithm on it.
	 * 
	 * @param p
	 */
	public BreadthFirst(Problem p) {

		this.problem = p;
		this.frontier = new ConcurrentLinkedQueue<Node>();
		frontier.add(new Node(p.initialState()));

		this.explored = new HashSet<States>();

		result = bfs();

	}

	/**
	 * The algorithm itself.
	 * 
	 * @param g
	 *            The state space of the problem
	 * @return A Node structure containing, upon success, the solution node. If no
	 *         solution was found, a Failure Node shall be returned
	 */
	private Result bfs() {

		while (true) {

			if (frontier.isEmpty())
				return failureRes;

			Node node = frontier.poll();
			explored.add(node.getState());

			for (Action action : problem.actions(node.getState())) {

				Node child = new Node(problem, node, action);

				if (!explored.contains(child.getState()) && !frontier.contains(child)) {

					if (problem.goalTest(child.getState())) // Goaltest
						return new Solution(child);

					frontier.add(child);
				}
			}
		}
	}

}