package task3.Genetic.Data;

import java.util.HashMap;

public class Clause {

	private HashMap<String, Integer> map;
	private HashMap<String, Boolean> neg;

	public Clause(HashMap<String, Integer> map) {
		this.map = map;
		neg = new HashMap<String, Boolean>();
	}

	public boolean evaluate(int individual) {
		int p;
		for (String st : neg.keySet()) {
			p = (1 << map.get(st));
			if (neg.get(st)) {
				if ((p & individual) != p)
					return true;
			} else {
				if ((p & individual) == p)
					return true;
			}
		}
		return false;
	}

	public void add(String s, boolean b) {
		this.neg.put(s, b);
	}

}
