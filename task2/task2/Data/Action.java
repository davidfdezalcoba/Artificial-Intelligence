package task2.Data;

/**
 * Action structure implementation.
 * 
 * @author david
 *
 */
public class Action {

	private final States v; // edge source
	private final States w; // edge target
	private final int weight; // edge weight
	private final String name;

	public Action(States v, States w, int weight, String name) {
		this.v = v;
		this.w = w;
		this.weight = weight;
		this.name = name;
	}

	public int weight() {
		return weight;
	}

	public States from() {
		return v;
	}

	public States to() {
		return w;
	}

	public String toString() {
		return this.name;
	}

	public Action reverse() {
		return new Action(this.w, this.v, this.weight, this.name);
	}

}