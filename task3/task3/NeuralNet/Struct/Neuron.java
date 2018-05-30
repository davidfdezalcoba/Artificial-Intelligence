package task3.NeuralNet.Struct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import task3.NeuralNet.Functions.ActivationFunction;
import task3.NeuralNet.Functions.InputSummingFunction;
import task3.NeuralNet.Functions.WeightedSumFunction;

public class Neuron implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	protected List<NeuronsConnection> inputConnections;
	protected List<NeuronsConnection> outputConnections;
	protected InputSummingFunction inputSummingFunction = new WeightedSumFunction();
	protected ActivationFunction activationFunction;
	private double output;
	private double totalInput;

	public Neuron(int id, ActivationFunction ac) {
		this.id = id;
		this.activationFunction = ac;
		this.inputConnections = new ArrayList<>();
		this.outputConnections = new ArrayList<>();
		this.output = 0;
	}

	public void addInputCon(NeuronsConnection nc) {
		this.inputConnections.add(nc);
	}

	public void addOutputCon(NeuronsConnection nc) {
		this.outputConnections.add(nc);
	}

	public void calculateOutput(double bias) {
		totalInput = inputSummingFunction.collectOutput(inputConnections) + bias;
		output = activationFunction.calculateOutput(totalInput);
	}

	public List<NeuronsConnection> getOutputCons() {
		return outputConnections;
	}

	public List<NeuronsConnection> getInputCons() {
		return inputConnections;
	}

	public double getOutput() {
		return output;
	}

	public void setOutput(double a) {
		this.output = a;
	}

	public void setIn(double a) {
		this.totalInput = a;
	}

	public double getIn() {
		return totalInput;
	}

	public int getId() {
		return id;
	}

	public String toString() {
		return Double.toString(output);
	}
}
