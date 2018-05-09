package src.Data;

import java.util.Stack;

public class Solution extends Result {

	private Stack<Node> sol;
	private Node sa;

	public Solution(Node s) {
		this.sa = s;
		this.sol = new Stack<Node>();
		Node n = s;
		while (n != null) {
			sol.push(n);
			n = n.getParent();
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s = "PathCost: " + sa.getPC() + ". ";
		while (!sol.isEmpty()) {
			Node n = sol.pop();
			if (n.getAction() != null) {
				s += "(" + n.getAction().toString() + ") ";
				s += " -> ";
			}
			s += n.toString();
			s += " ";
		}
		return s;
	}

}
