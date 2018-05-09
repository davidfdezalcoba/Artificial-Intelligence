package src.SearchAlgorithms;

import src.Data.Action;
import src.Data.Node;
import src.Data.Problem;
import src.Data.Result;
import src.Data.Solution;

public class DepthLimited extends SearchAlgorithm {

	private boolean cutoff;

	public DepthLimited() {
	}

	public DepthLimited(Problem p, int limit) {

		this.cutoff = false;
		this.result = dls(new Node(p.initialState()), p, limit);

	}

	private Result dls(Node node, Problem problem, int limit) {

		if (problem.goalTest(node.getState()))
			return new Solution(node);

		else if (limit == 0) {
			return cutoffRes;
		}

		else {

			cutoff = false;

			for (Action action : problem.actions(node.getState())) {

				Node child = new Node(problem, node, action);
				Result result = dls(child, problem, limit - 1);

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

	public Result getResult() {
		return result;
	}

}