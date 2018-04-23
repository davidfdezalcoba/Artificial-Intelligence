package src.SearchAlgorithms;

/**
 * Interface of a Search algorithm. On creation, a class implementing this
 * interface runs the algorithm for the problem given and stores the result. To
 * get the solution printed, the method printSolution must be called.
 * 
 * @author david
 *
 */
public interface SearchAlgorithm {

	/**
	 * Prints the solution provided by the specific search algorithm implementing
	 * this method.
	 */
	public void printSolution();

}
