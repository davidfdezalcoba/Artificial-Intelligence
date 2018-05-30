package task3.NeuralNet.Functions;

public interface ActivationFunction {

	double calculateOutput(double summedInput);

	double prime(double input);
}