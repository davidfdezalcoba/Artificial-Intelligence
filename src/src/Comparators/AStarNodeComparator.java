package src.Comparators;

import java.util.Comparator;

import src.Data.Node;
import src.Data.Problem;

public class AStarNodeComparator implements Comparator<Node> {

	private Problem p;

	public AStarNodeComparator(Problem p) {
		this.p = p;
	}

	@Override
	public int compare(Node arg0, Node arg1) {
		// TODO Auto-generated method stub
		int x = p.h(arg0) + arg0.getPC();
		int y = p.h(arg1) + arg1.getPC();
		if (x == y)
			return 0;
		else if (x < y)
			return -1;
		else
			return 1;
	}

}
