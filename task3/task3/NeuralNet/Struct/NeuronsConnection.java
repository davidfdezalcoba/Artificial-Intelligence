package task3.NeuralNet.Struct;

import java.io.Serializable;

public class NeuronsConnection implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Neuron fromNeuron;
	protected Neuron toNeuron;
	protected double weight;

	public NeuronsConnection(Neuron fromNeuron, Neuron toNeuron) {
		this.fromNeuron = fromNeuron;
		this.toNeuron = toNeuron;
		this.weight = Math.random();
	}

	public NeuronsConnection(Neuron fromNeuron, Neuron toNeuron, double weight) {
		this(fromNeuron, toNeuron);
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getWeightedInput() {
		return fromNeuron.getOutput() * weight;
	}

	public Neuron getFromNeuron() {
		return fromNeuron;
	}

	public Neuron getToNeuron() {
		return toNeuron;
	}

	public String toString() {
		return Double.toString(weight);
	}
}