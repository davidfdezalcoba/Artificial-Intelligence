package src.SearchAlgorithms;

import src.Data.Action;
import src.Data.Node;
import src.Data.Problem;
import src.Data.Result;
import src.Data.Solution;

public class IDAStar extends SearchAlgorithm {

	private int smallestCutoff;
	private boolean cutoff;
	private Problem problem;

	public IDAStar(Problem p) {

		this.problem = p;
		this.smallestCutoff = 530;
		this.cutoff = false;
		result = iddf();

	}

	private Result iddf() {
		Result result = cutoffRes;
		for (int i = 530; i < Integer.MAX_VALUE && result.equals(cutoffRes); i = smallestCutoff) {
			smallestCutoff = Integer.MAX_VALUE;
			result = dls(new Node(problem.initialState()), problem, i);
		}
		return result;
	}

	private Result dls(Node node, Problem problem, int limit) {

		if (problem.goalTest(node.getState()))
			return new Solution(node);

		else if (node.getPC() + problem.h(node) > limit) {
			smallestCutoff = node.getPC() + problem.h(node);
			return cutoffRes;
		}

		else {

			cutoff = false;

			for (Action action : problem.actions(node.getState())) {

				Node child = new Node(problem, node, action);
				Result result = dls(child, problem, limit);

				if (result.equals(cutoffRes))
					cutoff = true;
				else if (!result.equals(failureRes))
					return result;
			}
			if (cutoff)
				return cutoffRes;
			else
				return failureRes;
		}

	}
}
