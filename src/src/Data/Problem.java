package src.Data;

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
		g.addEdge(new Action(States.Szczecin, States.Gdansk, 320, "T1"));
		g.addEdge(new Action(States.Szczecin, States.Poznan, 300, "T2"));
		g.addEdge(new Action(States.Szczecin, States.Wroclaw, 330, "T3"));
		g.addEdge(new Action(States.Wroclaw, States.Opole, 100, "T4"));
		g.addEdge(new Action(States.Opole, States.Katowice, 80, "T5"));
		g.addEdge(new Action(States.Katowice, States.Krakow, 100, "T6"));
		g.addEdge(new Action(States.Poznan, States.Opole, 240, "T7"));
		g.addEdge(new Action(States.Poznan, States.Lodz, 250, "T8"));
		g.addEdge(new Action(States.Poznan, States.Warszawa, 300, "T9"));
		g.addEdge(new Action(States.Warszawa, States.Krakow, 300, "T10"));
		g.addEdge(new Action(States.Lodz, States.Krakow, 220, "T11"));
		g.addEdge(new Action(States.Gdansk, States.Olsztyn, 170, "T12"));
		g.addEdge(new Action(States.Olsztyn, States.Warszawa, 210, "T13"));
		g.addEdge(new Action(States.Poznan, States.Wroclaw, 180, "T14"));
		g.addEdge(new Action(States.Wroclaw, States.Opole, 100, "T15"));
		g.addEdge(new Action(States.Poznan, States.Lodz, 240, "T16"));
		g.addEdge(new Action(States.Katowice, States.Krakow, 95, "T17"));
		g.addEdge(new Action(States.Krakow, States.Poznan, 30, "T18"));
		g.addEdge(new Action(States.Warszawa, States.Szczecin, 560, "T19"));
	}

	/**
	 * The goal test for our problem. Given a node, checks if this node is a
	 * solution node.
	 * 
	 * @param s
	 *            The node to be checked
	 * @return True if the node is a solution. False otherwise.
	 */
	public boolean goalTest(States s) {
		return s.equals(States.Krakow);
	}

	public States result(States s, Action a) {
		return a.to();
	}

	public int stepCost(States s, Action a) {
		return a.weight();
	}

	public Iterable<Action> actions(States s) {
		return this.g.adj(s.ordinal());
	}

	public Graph getGraph() {
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
