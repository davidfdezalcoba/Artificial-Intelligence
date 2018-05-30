package task2.SearchAlgorithms;

import task2.Data.Cutoff;
import task2.Data.Failure;
import task2.Data.Result;

/**
 * Interface of a Search algorithm. On creation, a class implementing this
 * interface runs the algorithm for the problem given and stores the result. To
 * get the solution printed, the method printSolution must be called.
 * 
 * @author david
 *
 */
public class SearchAlgorithm {

	protected Result result;
	protected static final Failure failureRes = new Failure();
	protected static final Cutoff cutoffRes = new Cutoff();

	/**
	 * Prints the solution provided by the specific search algorithm implementing
	 * this method.
	 */
	public void printSolution() {
		System.out.println(result.toString() + '\n');
	}

}
