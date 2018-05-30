package task3.NeuralNet.Data;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Loader {

	static String trainingFile = "train-images.idx3-ubyte";
	static String trainingLabel = "train-labels.idx1-ubyte";
	static String testFile = "t10k-images.idx3-ubyte";
	static String testLabel = "t10k-labels.idx1-ubyte";

	int readInt(InputStream in) throws IOException {

		int d;
		int[] b = new int[4];

		for (int i = 0; i < 4; i++)
			b[i] = in.read();

		d = b[3] | b[2] << 8 | b[1] << 16 | b[0] << 24;
		return d;

	}

	List<Data> readDataFiles(String imageFile, String labelFile) throws IOException {

		List<Data> dataList = new ArrayList<>();
		int[] imageData;
		int[] labelData;
		int totalRows, totalCols, totalImages, totalLabels;

		try (InputStream in = new FileInputStream(imageFile)) {

			@SuppressWarnings("unused")
			int magic = readInt(in);
			totalImages = readInt(in);
			totalRows = readInt(in);
			totalCols = readInt(in);

			imageData = new int[totalImages * totalRows * totalCols];

			for (int i = 0; i < totalImages * totalRows * totalCols; i++)
				imageData[i] = in.read();
		}

		try (InputStream in = new FileInputStream(labelFile)) {

			@SuppressWarnings("unused")
			int magic = readInt(in);
			totalLabels = readInt(in);

			labelData = new int[totalLabels];

			for (int i = 0; i < totalLabels; i++)
				labelData[i] = in.read();
		}

		if (totalImages != totalLabels) // file corrupted
			return null;

		int ic = 0; // image data index counter
		int lc = 0; // label data index counter
		int count = 0;

		while (ic < imageData.length && lc < labelData.length) {

			int[] input;
			double[] result;

			input = new int[totalRows * totalCols];

			for (int i = 0; i < totalRows * totalCols; i++)
				input[i] = imageData[ic++];

			result = new double[10];

			for (int i = 0; i < 10; i++)
				result[i] = 0;

			result[labelData[lc++]] = 1.0;
			dataList.add(new Data(Integer.toString(count++), input, result));

		}

		return dataList;
	}

	List<Data> loadData(String imageFile, String labelFile) {
		List<Data> dataList;
		try {
			dataList = readDataFiles(imageFile, labelFile);
		} catch (java.io.IOException e) {
			System.out.println(e);
			dataList = null;
		}
		if (dataList == null)
			System.out.println("dataList null");
		return dataList;
	}

	public List<List<Data>> loadAllData() {
		// Return training and test data in a list of Data list.
		List<Data> trainingData = loadData(trainingFile, trainingLabel);
		List<Data> testData = loadData(testFile, testLabel);
		List<List<Data>> data = new ArrayList<>();
		data.add(trainingData);
		data.add(testData);
		return data;
	}

}