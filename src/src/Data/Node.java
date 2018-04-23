package src.Data;

import java.util.ArrayList;

/**
 * A class with the Node structure for the given problem.
 * 
 * @author david
 *
 */
public class Node {

	private States state;
	private int pathCost;
	private ArrayList<DirectedEdge> solution;

	/**
	 * Creates a new node with the state given, pathcost = 0 and an empty solution.
	 * 
	 * @param c
	 *            The state to be given to the node
	 */
	public Node(States c) {
		state = c;
		pathCost = 0;
		solution = new ArrayList<DirectedEdge>();
	}

	/**
	 * Creates a new node with state c, pathcost p, and a solution a
	 * 
	 * @param c
	 *            state
	 * @param p
	 *            pathcost
	 * @param a
	 *            solution
	 */
	public Node(States c, int p, ArrayList<DirectedEdge> a) {
		state = c;
		pathCost = p;
		solution = a;
	}

	public States getState() {
		return this.state;
	}

	public ArrayList<DirectedEdge> getSol() {
		return solution;
	}

	public int getPC() {
		return this.pathCost;
	}

	/**
	 * Checks if a node is in a goal state
	 * 
	 * @return - True if it is, False otherwise
	 */
	public boolean goalTest() {
		return this.state.equals(States.Krakow);
	}

	/**
	 * Prints a node to the stdout, giving: 
	 * - The route to follow to get to Krakowif it is a solution Node 
	 * - The state of Failure or Cutoff otherwise
	 */
	public void printNode() {
		if (solution.isEmpty())
			System.out.println(this.state);
		else {
			System.out.print("Szczecin");
			for (DirectedEdge w : solution) {
				System.out.print(" -> " + w);
			}
			System.out.println(". PathCost = " + pathCost);
		}
	}
}
