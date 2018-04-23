package src.Data;

import java.util.ArrayList;

import src.SearchAlgorithms.AStar;
import src.SearchAlgorithms.BreadthFirst;
import src.SearchAlgorithms.DepthFirst;
import src.SearchAlgorithms.DepthLimited;
import src.SearchAlgorithms.GreedyBF;
import src.SearchAlgorithms.IterativeDeepingDF;
import src.SearchAlgorithms.SearchAlgorithm;
import src.SearchAlgorithms.UniformCost;

/**
 * A class representing the problem of getting from Szczecin to Krakow.
 * 
 * @author david
 *
 */
public class Problem {

	/**
	 * A Graph implicitly defining the state space of the problem.
	 */
	private Graph g; // Implicitly defines the states, actions and transition model

	/**
	 * The initial state of the problem -> szczecin
	 */
	private final States initialState = States.Szczecin;

	/**
	 * Initializes the state space Graph by inserting the edges. (Actions)
	 */
	public Problem() {
		g = new Graph(10);
		g.addEdge(new DirectedEdge(States.Szczecin, States.Gdansk, 320, "T1"));
		g.addEdge(new DirectedEdge(States.Szczecin, States.Poznan, 300, "T2"));
		g.addEdge(new DirectedEdge(States.Szczecin, States.Wroclaw, 330, "T3"));
		g.addEdge(new DirectedEdge(States.Wroclaw, States.Opole, 100, "T4"));
		g.addEdge(new DirectedEdge(States.Opole, States.Katowice, 80, "T5"));
		g.addEdge(new DirectedEdge(States.Katowice, States.Krakow, 100, "T6"));
		g.addEdge(new DirectedEdge(States.Poznan, States.Opole, 240, "T7"));
		g.addEdge(new DirectedEdge(States.Poznan, States.Lodz, 250, "T8"));
		g.addEdge(new DirectedEdge(States.Poznan, States.Warszawa, 300, "T9"));
		g.addEdge(new DirectedEdge(States.Warszawa, States.Krakow, 300, "T10"));
		g.addEdge(new DirectedEdge(States.Lodz, States.Krakow, 220, "T11"));
		g.addEdge(new DirectedEdge(States.Gdansk, States.Olsztyn, 170, "T12"));
		g.addEdge(new DirectedEdge(States.Olsztyn, States.Warszawa, 210, "T13"));
		g.addEdge(new DirectedEdge(States.Poznan, States.Wroclaw, 180, "T14"));
		g.addEdge(new DirectedEdge(States.Wroclaw, States.Opole, 100, "T15"));
		g.addEdge(new DirectedEdge(States.Poznan, States.Lodz, 240, "T16"));
		g.addEdge(new DirectedEdge(States.Katowice, States.Krakow, 95, "T17"));
		g.addEdge(new DirectedEdge(States.Krakow, States.Poznan, 30, "T18"));
		g.addEdge(new DirectedEdge(States.Warszawa, States.Szczecin, 560, "T19"));
	}

	/**
	 * The goal test for our problem. Given a node, checks if this node is a
	 * solution node.
	 * 
	 * @param s
	 *            The node to be checked
	 * @return True if the node is a solution. False otherwise.
	 */
	public boolean goalTest(Node s) {
		return s.getState().equals(States.Krakow);
	}

	/**
	 * Returns the pathCost of a particular solution.
	 * 
	 * @param solution
	 *            The solution to extract the cost from
	 * @return An integer with the pathCost.
	 */
	public int pathCost(ArrayList<DirectedEdge> solution) {
		int c = 0;
		for (DirectedEdge w : solution) {
			c = c + w.weight();
		}
		return c;
	}

	/**
	 * Solves the problem for the different algorithms required.
	 */
	public void solve(SearchAlgorithm s) {
		s.printSolution();
		new BreadthFirst(this).printSolution();
		new DepthFirst(this).printSolution();
		new UniformCost(this).printSolution();
		new DepthLimited(this, 4).printSolution();
		new IterativeDeepingDF(this).printSolution();
		new GreedyBF(this).printSolution();
		new AStar(this).printSolution();
	}

	/**
	 * 
	 * @return a Graph object representing the state space of the problem
	 */
	public Graph getStateSpace() {
		return this.g;
	}

	/**
	 * 
	 * @return The initial state of the problem.
	 */
	public States initialState() {
		return this.initialState;
	}

	/**
	 * Function representing the staight-line distance heuristic for our problem.
	 * 
	 * @param s
	 *            The state we are in
	 * @return An int which represents the distance between our state and Krakow. 0
	 *         in case of failure.
	 */
	public int h(Node s) {
		switch (s.getState()) {
		case Gdansk:
			return 485;
		case Katowice:
			return 70;
		case Krakow:
			return 0;
		case Lodz:
			return 190;
		case Olsztyn:
			return 415;
		case Opole:
			return 160;
		case Poznan:
			return 335;
		case Szczecin:
			return 530;
		case Warszawa:
			return 250;
		case Wroclaw:
			return 240;
		default:
			return 0;
		}
	}

}
