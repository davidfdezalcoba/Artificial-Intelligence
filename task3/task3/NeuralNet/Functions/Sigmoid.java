package task3.NeuralNet.Functions;

import java.io.Serializable;

public class Sigmoid implements ActivationFunction, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Sigmoid() {
	}

	@Override
	public double calculateOutput(double summedInput) {
		// TODO Auto-generated method stub
		return (1 / (1 + Math.exp(-1.0 * summedInput)));
	}

	public double prime(double input) {
		// TODO Auto-generated method stub
		double n = calculateOutput(input);
		return n * (1 - n);
	}

}
