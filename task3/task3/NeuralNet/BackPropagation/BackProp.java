package task3.NeuralNet.BackPropagation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import task3.NeuralNet.Main;
import task3.NeuralNet.Data.Data;
import task3.NeuralNet.Functions.ActivationFunction;
import task3.NeuralNet.Functions.Sigmoid;
import task3.NeuralNet.Struct.NeuralNet;
import task3.NeuralNet.Struct.Neuron;
import task3.NeuralNet.Struct.NeuronsConnection;

public class BackProp {

	private HashMap<Neuron, Double> delta;
	private ActivationFunction g;
	private NeuralNet neural;
	private List<List<Data>> data;

	private final Random rand = new Random();

	public BackProp(List<List<Data>> data, NeuralNet nn) throws IOException {

		this.data = data;
		this.neural = nn;
		g = new Sigmoid();
		delta = new HashMap<Neuron, Double>();

	}

	public void backpropagate() throws IOException {

		List<Data> examples = data.get(0);
		List<NeuronsConnection> weights = neural.getWeights();
		List<Neuron> inputLayerNeurons = neural.getInputLayer().getNeurons();
		List<Neuron> hiddenLayerNeurons = neural.getHiddenLayers().get(0).getNeurons();
		List<Neuron> outputLayerNeurons = neural.getOutputLayer().getNeurons();
		double[] biasOut = neural.getOutputLayer().getBias();
		double[] biasHid = neural.getHiddenLayers().get(0).getBias();

		// Init biases
		for (int i = 0; i < Main.NUM_HU; i++)
			biasHid[i] = rand.nextGaussian();

		for (int i = 0; i < 10; i++)
			biasOut[i] = rand.nextGaussian();

		// Init weights
		for (NeuronsConnection nc : weights)
			nc.setWeight(rand.nextGaussian());

		for (int epoch = 0; epoch < Main.NUM_EP; epoch++) {

			Collections.shuffle(examples);

			for (Data ex : examples) {

				// Feed forward
				for (Neuron i : inputLayerNeurons)
					i.setOutput(ex.getInput()[i.getId()]);

				for (Neuron j : hiddenLayerNeurons)
					j.calculateOutput(biasHid[j.getId()]);

				for (Neuron j : outputLayerNeurons)
					j.calculateOutput(biasOut[j.getId()]);

				// Back propagation
				for (Neuron j : outputLayerNeurons) {
					double d = g.prime(j.getIn()) * (ex.getResult()[j.getId()] - j.getOutput());
					delta.put(j, d);
					biasOut[j.getId()] = d; // Update bias
				}

				for (Neuron i : hiddenLayerNeurons) {
					double sum = 0;

					for (NeuronsConnection j : i.getOutputCons())
						sum += j.getWeight() * delta.get(j.getToNeuron());

					double d = g.prime(i.getIn()) * sum;
					delta.put(i, d);
					biasHid[i.getId()] = d; // Update bias
				}

				// Update weights
				for (NeuronsConnection nc : weights) {
					double nw = (Main.ALPHA * nc.getFromNeuron().getOutput() * delta.get(nc.getToNeuron()));
					nc.setWeight(nc.getWeight() + nw);
				}

			}

			System.out.println("Epoch " + (epoch + 1) + "/100. " + evaluate(data.get(1)) + "/10000");
			FileOutputStream fos = new FileOutputStream("NeuralNet.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(neural);
			oos.close();

		}
	}

	private int evaluate(List<Data> data) {

		int correct = 0;

		for (int i = 0; i < data.size(); i++) {
			Data d = data.get(i);
			double[] output = new double[10];
			output = neural.feedForward(d.getInput());
			int maxResultRow = 0;
			int maxOutputRow = 0;
			for (int j = 0; j < 10; j++) {
				if (d.getResult()[j] > d.getResult()[maxResultRow])
					maxResultRow = j;
				if (output[j] > output[maxOutputRow])
					maxOutputRow = j;
			}
			if (maxResultRow == maxOutputRow)
				correct += 1;
		}
		return correct;
	}

}
