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

		int arg = 0;
		for (String s : args) {
			switch (s) {
			case "bfs":
				new BreadthFirst(p).printSolution();
				break;
			case "dfs":
				new DepthFirst(p).printSolution();
				break;
			case "dlfs":
				new DepthLimited(p, 3).printSolution();
				break;
			case "dlfslimit":
				new DepthLimited(p, Integer.parseInt(args[arg + 1])).printSolution();
				break;
			case "iddfs":
				new IterativeDeepingDF(p).printSolution();
				break;
			case "uc":
				new UniformCost(p).printSolution();
				break;
			case "idastar":
				new IDAStar(p).printSolution();
				break;
			case "astar":
				new AStar(p).printSolution();
				break;
			case "gbs":
				new GreedyBF(p).printSolution();
				break;
			case "bd":
				new BiDirectional(p).printSolution();
				break;
			case "all":
				new BreadthFirst(p).printSolution();
				new DepthFirst(p).printSolution();
				new DepthLimited(p, 3).printSolution();
				new IterativeDeepingDF(p).printSolution();
				new UniformCost(p).printSolution();
				// new IDAStar(p).printSolution();
				new AStar(p).printSolution();
				new GreedyBF(p).printSolution();
				new BiDirectional(p).printSolution();
				break;
			default:
			}
			arg++;
		}

	}

}
