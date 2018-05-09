package src.SearchAlgorithms;

import java.util.HashSet;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import src.Data.Action;
import src.Data.Failure;
import src.Data.Graph;
import src.Data.Node;
import src.Data.Problem;
import src.Data.Result;
import src.Data.Solution;
import src.Data.States;

public class BiDirectional extends SearchAlgorithm {

	private boolean fromX;
	private Problem problem;
	private final Result failure = new Failure();
	private Queue<Node> p, q;
	private HashSet<Node> exploredP, exploredQ;

	public BiDirectional(Problem p) {

		problem = p;
		this.p = new ConcurrentLinkedQueue<Node>();
		this.q = new ConcurrentLinkedQueue<Node>();

		this.fromX = false;

		this.exploredP = new HashSet<Node>();
		this.exploredQ = new HashSet<Node>();

		Node initialp = new Node(p.initialState());
		Node initialq = new Node(States.Krakow);

		this.p.add(initialp);
		q.add(initialq);
		result = bfs(p.getGraph());

	}

	private Result bfs(Graph g) {

		boolean found = false;

		Graph t = g.transpose();

		while (true) {

			if (q.isEmpty() || p.isEmpty())
				return this.failure;

			Node x = p.poll();
			Node y = q.poll();

			Node inter = null;

			exploredP.add(x);
			exploredQ.add(y);

			for (Action action : problem.actions(x.getState())) {

				// Create child node

				Node childx = new Node(problem, x, action);

				if (!exploredP.contains(childx) && !p.contains(childx)) {

					if (q.contains(childx)) { // Intersection found
						inter = childx;
						found = true;
						fromX = true;
					}

					p.add(childx);

				}
			}

			for (Action action : t.adj(y.getState().ordinal())) {
				// Create child node

				Node childy = new Node(problem, y, action);

				if (!exploredQ.contains(childy) && !q.contains(childy)) {

					if (p.contains(childy)) { // Intersection found
						found = true;
						inter = childy;
					}

					q.add(childy);
				}

			}

			if (found) {

				if (fromX) { // The intersection node has the path from the origin to the middle

					// fromQ <- The other half of the way (From end to middle)
					Node fromQ = q.poll();
					while (!fromQ.equals(inter)) {
						fromQ = q.poll();
					}

					// Complete, in fin, the way, adding to inter the actions of fromQ reversed.
					Node fin = inter;
					while (fromQ.getParent() != null) {
						fin = new Node(problem, fin, fromQ.getAction().reverse());
						fromQ = fromQ.getParent();
					}
					return new Solution(fin);

				} else { // The intersection node has the path from the end to the middle

					// fromP <- The other half of the way (From origin to middle)
					Node fromP = q.poll();
					while (!fromP.equals(inter)) {
						fromP = q.poll();
					}

					// Complete, in fin, the way, adding to fromP, the actions of inter reversed.
					Node fin = fromP;
					while (inter.getParent() != null) {
						fin = new Node(problem, fin, inter.getAction().reverse());
						inter = inter.getParent();
					}
					return new Solution(fin);
				}

			}

		}

	}

}
