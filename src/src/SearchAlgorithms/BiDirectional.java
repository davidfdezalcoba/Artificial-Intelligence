package src.SearchAlgorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import src.Data.DirectedEdge;
import src.Data.Graph;
import src.Data.Node;
import src.Data.Problem;
import src.Data.States;

public class BiDirectional implements SearchAlgorithm {

	private Problem pr;
	private ArrayList<DirectedEdge>[] pathP, pathQ;
	private final Node failureNode = new Node(States.Failure);
	private Node solutionNode;
	private Queue<Node> p, q;
	private ArrayList<States> markedp, markedq;

	@SuppressWarnings("unchecked")
	public BiDirectional(Problem p) {

		pr = p;
		this.p = new ConcurrentLinkedQueue<Node>();
		this.q = new ConcurrentLinkedQueue<Node>();
		pathP = (ArrayList<DirectedEdge>[]) new ArrayList[p.getStateSpace().V()];
		for (int v = 0; v < p.getStateSpace().V(); v++)
			pathP[v] = new ArrayList<DirectedEdge>();
		pathQ = (ArrayList<DirectedEdge>[]) new ArrayList[p.getStateSpace().V()];
		for (int v = 0; v < p.getStateSpace().V(); v++)
			pathQ[v] = new ArrayList<DirectedEdge>();
		this.markedp = new ArrayList<States>();
		this.markedq = new ArrayList<States>();
		Node initialp = new Node(p.initialState());
		Node initialq = new Node(States.Krakow);
		this.p.add(initialp);
		q.add(initialq);
		this.solutionNode = bfs(p.getStateSpace());

	}

	private Node bfs(Graph g) {

		boolean found = false;
		Graph t = g.transpose();

		while (true) {

			if (q.isEmpty() || p.isEmpty())
				return this.failureNode;

			Node x = p.poll();
			Node y = q.poll();
			Node inter = null;
			markedp.add(x.getState());
			markedq.add(y.getState());
			pathP[x.getState().ordinal()] = x.getSol();
			pathQ[y.getState().ordinal()] = y.getSol();

			for (DirectedEdge w : g.adj(x.getState().ordinal())) {
				// Create child node
				ArrayList<DirectedEdge> tmp = new ArrayList<DirectedEdge>(x.getSol());
				tmp.add(w);
				Node x2 = new Node(w.to(), x.getPC() + w.weight(), tmp);
				pathP[x2.getState().ordinal()] = tmp;

				if (!markedp.contains(x2.getState()) && !p.contains(x2)) {

					if (markedq.contains(x2.getState())) {
						inter = x2;
						found = true;
					}

					p.add(x2);

				}
			}

			for (DirectedEdge w : t.adj(y.getState().ordinal())) {
				// Create child node
				ArrayList<DirectedEdge> tmp = new ArrayList<DirectedEdge>(y.getSol());
				tmp.add(w);
				Node y2 = new Node(w.to(), y.getPC() + w.weight(), tmp);
				pathQ[y2.getState().ordinal()] = tmp;

				if (!markedq.contains(y2.getState()) && !q.contains(y2)) {

					if (markedp.contains(y2.getState())) {
						found = true;
						inter = y2;
					}

					q.add(y2);
				}

			}

			if (found) {
				ArrayList<DirectedEdge> o = new ArrayList<DirectedEdge>();
				for (DirectedEdge w : pathQ[inter.getState().ordinal()]) {
					o.add(w);
				}
				Collections.reverse(o);
				if (pathP[inter.getState().ordinal()].addAll(o)) {
					return new Node(States.Krakow, pr.pathCost(pathP[inter.getState().ordinal()]),
							pathP[inter.getState().ordinal()]);
				}
			}

		}

	}

	@Override
	public void printSolution() {
		// TODO Auto-generated method stub
		System.out.println("Bi-Directional Search: ");
		this.solutionNode.printNode();
	}

}
