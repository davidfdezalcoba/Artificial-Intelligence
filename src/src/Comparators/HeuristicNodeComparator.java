package src.Comparators;

import java.util.Comparator;

import src.Data.Node;
import src.Data.Problem;

public class HeuristicNodeComparator implements Comparator<Node> {

	private Problem p;

	public HeuristicNodeComparator(Problem p) {
		this.p = p;
	}

	@Override
	public int compare(Node arg0, Node arg1) {
		// TODO Auto-generated method stub
		if (p.h(arg0) == p.h(arg1))
			return 0;
		else if (p.h(arg0) < p.h(arg1))
			return -1;
		else
			return 1;
	}

}
