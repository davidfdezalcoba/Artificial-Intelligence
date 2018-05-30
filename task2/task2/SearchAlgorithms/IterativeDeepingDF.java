package task2.SearchAlgorithms;

import task2.Data.Problem;
import task2.Data.Result;

public class IterativeDeepingDF extends SearchAlgorithm {

	private Problem problem;

	public IterativeDeepingDF(Problem p) {
		this.problem = p;
		result = iddf();
	}

	private Result iddf() {
		Result result = cutoffRes;
		for (int i = 0; i < Integer.MAX_VALUE && result.equals(cutoffRes); i++) {
			result = (new DepthLimited(problem, i).getResult());
		}
		return result;
	}
}
