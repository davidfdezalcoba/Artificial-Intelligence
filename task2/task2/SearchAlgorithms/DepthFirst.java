package task2.SearchAlgorithms;

import java.util.HashSet;
import java.util.Stack;

import task2.Data.Action;
import task2.Data.Node;
import task2.Data.Problem;
import task2.Data.Result;
import task2.Data.Solution;
import task2.Data.States;

public class DepthFirst extends SearchAlgorithm {

	private HashSet<States> explored;
	private boolean reached;

	/**
	 * This constructor takes the problem to be solved and runs dfs on it.
	 * 
	 * @param p
	 *            The problem to be solved.
	 */
	public DepthFirst(Problem p) {

		reached = false;
		new Stack<Node>();
		this.explored = new HashSet<States>();
		result = dfs(p, new Node(p.initialState()));

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
	private Result dfs(Problem problem, Node node) {

		Result sol = failureRes;

		explored.add(node.getState());

		for (Action action : problem.actions(node.getState())) {

			if (!reached) {

				Node child = new Node(problem, node, action);

				if (!explored.contains(child.getState())) {

					if (problem.goalTest(child.getState())) {
						reached = true;
						return new Solution(child);
					}

					sol = dfs(problem, child);
				}
			}
		}

		return sol;

	}

}