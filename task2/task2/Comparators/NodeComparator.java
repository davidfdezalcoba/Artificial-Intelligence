package task2.Comparators;

import java.util.Comparator;

import task2.Data.Node;

public class NodeComparator implements Comparator<Node> {

	@Override
	public int compare(Node arg0, Node arg1) {
		// TODO Auto-generated method stub
		if (arg0.getPC() == arg1.getPC())
			return 0;
		else if (arg0.getPC() < arg1.getPC())
			return -1;
		else
			return 1;
	}

}
