package task3.Genetic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import task3.Genetic.Data.Clause;

public class Genetic {

	private static final int POP_SIZE = 100;
	private static final int EVO_STEPS = 80;
	private static final int SURVIVAL_RATE = 40;

	private boolean satisfiable; // Whether we have a solution
	private int solution; // The solution provided in case it is found
	private int numclauses; // Number of clauses of the CNF
	private HashMap<String, Integer> map; // A map <Literal, Integer> for getting the position
											// of the bit representation of the literal
	private int literals; // Number of literals in the clause
	private ArrayList<Clause> clauses; // The list of clauses
	private int[] members_for_reproduction; // An array containing the members of the population
											// chosen to reproduce
	private int[] population; // The current population
	private HashMap<Integer, ArrayList<Integer>> fitness; // A mapping from the fitness range to a
															// list of individuals that satisfy that fitness
	private int time; // The current evolution step
	private Random random; // A randomizer

	public Genetic(String formula) {

		// Init atributes.
		random = new Random();
		members_for_reproduction = new int[SURVIVAL_RATE];
		clauses = new ArrayList<Clause>();
		map = new HashMap<String, Integer>();
		population = new int[POP_SIZE];
		fitness = new HashMap<Integer, ArrayList<Integer>>();

		// Parse CNF
		parse(formula);

		numclauses = clauses.size();

		for (int i = 0; i <= numclauses; i++) {
			fitness.put(i, new ArrayList<Integer>());
		}

		time = 0;

		// Init Population
		init_population();

		while (!satisfiable && time < EVO_STEPS) {

			for (int i = 0; i < POP_SIZE; i++) {

				int individual = population[i];
				fitness.get(fitness(individual)).add(individual); // Calculate fitness of individual

				if (!fitness.get(numclauses).isEmpty()) { // If there is an indiv. w/ max fitness, stop.
					satisfiable = true;
					solution = individual;
					break;
				}
			}

			if (satisfiable)
				break;

			select_members(); // Select members for reproduction
			population = replace_population(); // Create the new population
			time++;
		}

		if (satisfiable) {

			// Print the solution in reverse order because the ints representing the truth
			// values of the
			// literals are processed from right to left
			String s = new StringBuilder(Integer.toBinaryString(solution)).reverse().toString();
			System.out.println("Problem Satisfiable. One solution: " + s);

		} else
			System.out.println("Unsatisfiable.");
	}

	// Calculates the fitness of an individual
	private int fitness(int individual) {

		int ret = 0;

		for (Clause c : clauses) {
			if (c.evaluate(individual))
				ret++;
		}

		return ret;
	}

	private void parse(String formula) {

		String[] tokens = formula.split("&&"); // Get the clauses

		for (String t : tokens) {

			Clause c = new Clause(map);
			clauses.add(c);
			t = t.substring(1, t.length() - 1); // Eliminate parenthesis
			String[] lits = t.split("\\|\\|"); // Get the literals

			for (String lit : lits) {

				if ((lit.charAt(0) == '!')) { // Handle negation

					if (!map.containsKey(lit.substring(1)))
						map.put(lit.substring(1), literals++);

					c.add(lit.substring(1), true);

				} else {

					if (!map.containsKey(lit))
						map.put(lit, literals++);

					c.add(lit, false);
				}
			}
		}
	}

	private void init_population() {

		int q = (int) Math.pow(2, literals);

		for (int i = 0; i < POP_SIZE; i++)
			population[i] = random.nextInt(q); // Generate random numbers from 0 to 2^literals - 1

	}

	private void select_members() {

		// Calculate average fitness
		int av = 0;

		for (int fitn : fitness.keySet()) {
			av += fitn * fitness.get(fitn).size();
		}

		av /= POP_SIZE;

		for (int i = 0; i < SURVIVAL_RATE; i++) {

			double prob = random.nextDouble();

			if (prob > 0.3) { // Give candidates above a average a 0.7 prob of reproducing

				int fi = random.nextInt(numclauses - av) + av; // Generate a random number between average
																// and max fitness
				if (fitness.get(fi).size() > 0) { // If there is at least one member with the random fitness
					// Choose randomly one among those with the same fitness
					int ra = random.nextInt(fitness.get(fi).size());
					members_for_reproduction[i] = fitness.get(fi).get(ra);
				} else
					i--;

			} else {

				int fi = random.nextInt(av);
				if (fitness.get(fi).size() > 0) {
					int ra = random.nextInt(fitness.get(fi).size());
					members_for_reproduction[i] = fitness.get(fi).get(ra);
				} else
					i--;
			}
		}
	}

	private int crossover(int p1, int p2) {
		int d = literals / 2; // Half of the bits
		int q = (int) (Math.pow(2, d) - 1); // Mask for tail of p2. Example 0b00001111

		return ((p1 >> d) << d) | (p2 & q); // First half of p1 ++ second half of p2
	}

	private int mutation(int p) {

		return p ^ (1 << random.nextInt(literals)); // Flip a random bit

	}

	private int[] replace_population() {

		int[] r = new int[POP_SIZE];

		for (int i = 0; i < POP_SIZE; i++) {

			int p1 = random.nextInt(SURVIVAL_RATE); // Choose one parent from the selected
			int p2 = random.nextInt(SURVIVAL_RATE); // Choose another parent
			double prob = random.nextDouble(); // Choose probability for either crossover or mutation

			if (prob > 0.5 && i < POP_SIZE - 1) { // 0.5 probability for each

				r[i] = crossover(members_for_reproduction[p1], members_for_reproduction[p2]);
				r[++i] = crossover(members_for_reproduction[p2], members_for_reproduction[p1]);

			} else
				r[i] = mutation(members_for_reproduction[random.nextInt(SURVIVAL_RATE)]);
		}

		// Clear the previous fitness mapping
		for (int i = 0; i <= numclauses; i++) {
			fitness.get(i).clear();
		}

		return r;
	}

	public HashMap<String, Integer> getMap() {
		return this.map;
	}

}
