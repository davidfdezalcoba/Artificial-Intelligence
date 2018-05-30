package task3.NeuralNet.Data;

public class Data {

	String id;
	int[] input; // The input image as 28 X 28 matrix
	double[] result; // The value of the number in image as 10 x 1 vector

	Data(String id, int[] input, double[] result) {
		this.id = id;
		this.input = input;
		this.result = result;
	}

	public int[] getInput() {
		return input;
	}

	public double[] getResult() {
		return result;
	}

}