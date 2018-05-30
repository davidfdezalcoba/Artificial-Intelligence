package task3.NeuralNet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import task3.NeuralNet.BackPropagation.BackProp;
import task3.NeuralNet.Data.Data;
import task3.NeuralNet.Data.Loader;
import task3.NeuralNet.Functions.Sigmoid;
import task3.NeuralNet.Struct.NeuralNet;
import task3.NeuralNet.Struct.NeuralNetLayer;
import task3.NeuralNet.Struct.Neuron;
import task3.NeuralNet.Struct.NeuronsConnection;

public class Main {

	private static NeuralNetLayer inputLayer;
	private static NeuralNetLayer outputLayer;
	private static ArrayList<NeuralNetLayer> hiddenLayers;

	public static final int NUM_HU = 10;
	public static final int NUM_EP = 100;
	public static final double ALPHA = 0.005;

	public static void main(String[] args) throws IOException {

		List<List<Data>> data;
		System.out.println("Loading data...");
		Loader loader = new Loader();
		data = loader.loadAllData();

		System.out.println("Neural Net written in NeuralNet.ser after each epoch");
		System.out.println("Finished loading data.");

		initLayers();

		NeuralNet nn = new NeuralNet("Mnist", inputLayer, hiddenLayers, outputLayer);
		BackProp bp = new BackProp(data, nn);
		bp.backpropagate();

	}

	private static void initLayers() {
		ArrayList<Neuron> inputList = new ArrayList<Neuron>();
		for (int i = 0; i < 784; i++) {
			inputList.add(new Neuron(i, new Sigmoid()));
		}
		inputLayer = new NeuralNetLayer("Input", inputList);

		ArrayList<Neuron> hiddenList = new ArrayList<Neuron>();
		for (int i = 0; i < NUM_HU; i++) {
			hiddenList.add(new Neuron(i, new Sigmoid()));
		}
		NeuralNetLayer hidden = new NeuralNetLayer("hidden", hiddenList, new double[NUM_HU]);
		hiddenLayers = new ArrayList<NeuralNetLayer>();
		hiddenLayers.add(hidden);

		ArrayList<Neuron> outputList = new ArrayList<Neuron>();
		for (int i = 0; i < 10; i++) {
			outputList.add(new Neuron(i, new Sigmoid()));
		}
		outputLayer = new NeuralNetLayer("Output", outputList, new double[10]);

		for (Neuron in : inputLayer.getNeurons()) {
			for (Neuron hn : hiddenLayers.get(0).getNeurons()) {
				NeuronsConnection nc = new NeuronsConnection(in, hn);
				hn.addInputCon(nc);
				in.addOutputCon(nc);
			}
		}

		for (Neuron hn : hiddenLayers.get(0).getNeurons()) {
			for (Neuron on : outputLayer.getNeurons()) {
				NeuronsConnection nc = new NeuronsConnection(hn, on);
				on.addInputCon(nc);
				hn.addOutputCon(nc);
			}
		}
	}

}