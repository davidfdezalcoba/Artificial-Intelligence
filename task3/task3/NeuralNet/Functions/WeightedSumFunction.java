package task3.NeuralNet.Functions;

import java.io.Serializable;
import java.util.List;

import task3.NeuralNet.Struct.NeuronsConnection;

public final class WeightedSumFunction implements InputSummingFunction, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public double collectOutput(List<NeuronsConnection> inputConnections) {
		double weightedSum = 0d;
		for (NeuronsConnection connection : inputConnections) {
			weightedSum += connection.getWeightedInput();
		}
		return weightedSum;
	}
}