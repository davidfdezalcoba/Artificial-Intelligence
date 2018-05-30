package task3.NeuralNet.Struct;

import java.io.Serializable;
import java.util.ArrayList;

public class NeuralNetLayer implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	protected ArrayList<Neuron> neurons;
	protected double[] bias;

	public NeuralNetLayer(String id) {
		this.id = id;
		neurons = new ArrayList<Neuron>();
	}

	public NeuralNetLayer(String id, ArrayList<Neuron> neurons) {
		this.id = id;
		this.neurons = neurons;
		this.bias = null;
	}

	public NeuralNetLayer(String id, ArrayList<Neuron> neurons, double[] bias) {
		this.id = id;
		this.neurons = neurons;
		this.bias = bias;
	}

	public ArrayList<Neuron> getNeurons() {
		return neurons;
	}

	public double[] getBias() {
		return bias;
	}

	public String toString() {
		return id;
	}
}