package task2.Data;

/**
 * A class with the Node structure for the given problem.
 * 
 * @author david
 *
 */
public class Node {

	private States state;
	private Node parent;
	private Action action;
	private int pathCost;

	/**
	 * Creates a new node with the state given, pathcost = 0 and an empty solution.
	 * 
	 * @param c
	 *            The state to be given to the node
	 */
	public Node(States c) {
		state = c;
		pathCost = 0;
		parent = null;
		action = null;
	}

	public Node(Problem problem, Node parent, Action action) {
		this.state = problem.result(parent.getState(), action);
		this.parent = parent;
		this.action = action;
		this.pathCost = parent.getPC() + problem.stepCost(parent.getState(), action);
	}

	public States getState() {
		return this.state;
	}

	public int getPC() {
		return this.pathCost;
	}

	public Node getParent() {
		return this.parent;
	}

	public Action getAction() {
		return this.action;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (other == this)
			return true;
		if (!(other instanceof Node))
			return false;
		Node otherMyClass = (Node) other;
		return otherMyClass.getState().equals(this.state);
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 17 * hash + (this.state != null ? this.state.hashCode() : 0);
		return hash;
	}

	public String toString() {
		return this.state.toString();
	}

}
