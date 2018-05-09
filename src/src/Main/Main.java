package src.Main;

import src.Data.Problem;
import src.SearchAlgorithms.AStar;
import src.SearchAlgorithms.BiDirectional;
import src.SearchAlgorithms.BreadthFirst;
import src.SearchAlgorithms.DepthFirst;
import src.SearchAlgorithms.DepthLimited;
import src.SearchAlgorithms.GreedyBF;
import src.SearchAlgorithms.IDAStar;
import src.SearchAlgorithms.IterativeDeepingDF;
import src.SearchAlgorithms.UniformCost;

public class Main {

	/**
	 * Main program. Creates the problem and solves it.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Problem p = new Problem();

		System.out.println("Breath First Search: ");
		new BreadthFirst(p).printSolution();
		System.out.println("Depth First Search: ");
		new DepthFirst(p).printSolution();
		System.out.println("Iterative Deeping Depth First Search: ");
		new IterativeDeepingDF(p).printSolution();
		System.out.println("Uniform Cost Search: ");
		new UniformCost(p).printSolution();
		System.out.println("IDA* Search: ");
		new IDAStar(p).printSolution();
		System.out.println("A* Search: ");
		new AStar(p).printSolution();
		System.out.println("Greedy Best-First Search: ");
		new GreedyBF(p).printSolution();
		System.out.println("Bidirectional Search: ");
		new BiDirectional(p).printSolution();

		System.out.println("Depth Limited Search: ");
		if (args.length == 1) {
			int limit = Integer.parseInt(args[0]);
			new DepthLimited(p, limit).printSolution();
		} else
			new DepthLimited(p, 4).printSolution();

	}

}
