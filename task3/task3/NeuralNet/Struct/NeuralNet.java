package task3.NeuralNet.Struct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NeuralNet implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private NeuralNetLayer inputLayer;
	private ArrayList<NeuralNetLayer> hiddenLayers;
	private NeuralNetLayer outputLayer;

	public NeuralNet(String id, NeuralNetLayer inputLayer, ArrayList<NeuralNetLayer> hiddenLayers,
			NeuralNetLayer outputLayer) {
		this.id = id;
		this.inputLayer = inputLayer;
		this.hiddenLayers = hiddenLayers;
		this.outputLayer = outputLayer;
	}

	public NeuralNet(String id, NeuralNetLayer inputLayer, NeuralNetLayer outputLayer) {
		this.id = id;
		this.inputLayer = inputLayer;
		this.outputLayer = outputLayer;
	}

	public NeuralNetLayer getInputLayer() {
		return inputLayer;
	}

	public NeuralNetLayer getOutputLayer() {
		return outputLayer;
	}

	public ArrayList<NeuralNetLayer> getHiddenLayers() {
		return hiddenLayers;
	}

	public List<NeuronsConnection> getWeights() {
		List<NeuronsConnection> l = new ArrayList<NeuronsConnection>();
		for (Neuron n : this.inputLayer.neurons) {
			for (NeuronsConnection nc : n.outputConnections)
				l.add(nc);
		}

		for (NeuralNetLayer nl : this.hiddenLayers) {
			for (Neuron n : nl.neurons) {
				for (NeuronsConnection nc : n.outputConnections) {
					l.add(nc);
				}
			}
		}
		return l;
	}

	public double[] feedForward(int[] input) {
		double[] res = new double[10];
		int i = 0;
		for (Neuron in : inputLayer.neurons) {
			if (in.getId() != 784)
				in.setOutput(input[in.getId()]);
		}
		for (Neuron j : hiddenLayers.get(0).neurons) {
			if (j.getId() != 10)
				j.calculateOutput(hiddenLayers.get(0).bias[j.getId()]);
		}

		for (Neuron j : outputLayer.neurons) {
			j.calculateOutput(outputLayer.bias[j.getId()]);
			res[i++] = j.getOutput();
		}
		return res;
	}

	public String toString() {
		return id;
	}
}
