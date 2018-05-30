# Artificial-Intelligence

Artificial Intelligence project for UAM. Author: David Fernández Alcoba.

The Diagrams for the first task are in the images:

  - Semantic Network in Semantic_Network.jpg // A semantic network with animals, humans, and relations
  - Conceptual Graph in Conceptual_Graph.jpg // A conceptual Graph from a sentence stated at the bottom of the picture
  - Frame System in Frame System.jpg         // A Frame System about Football as a sport
  
The task2 folder contains the source code for the search algorithms.

task3 folder contains the source code for:
   - task3.Genetic.* genetic algorithm
   - task3.NeuralNet.* neural net
   - task3.CandidateElim.* candidate elimination
   
#Genetic Algorithm.

Uses a dummy parser which accepts CNF's in the following manner:
Literals with a char.
Negation with a "!" before the char
Logical or's with "||"
Logical and's with "&&"
Parenthesis between clauses are necessary
Example:

	(a||!b)&&(c||!c)
For new formulas they must be changed in the Main class of the task3.Genetic package
	
#Neural Net.

Loads MNIST database files and trains the network according to three hiperparameters:
	
	ALPHA - Learning Rate
	NUM_HU - Number of hidden units
	NUM_EP - Number of epochs to try
	
For each epoch in NUM_EP, the neural net tests its knowledge with the test MNIST files, giving the number of successes.
	
Having played a little with different values and taking into account that the backprop algorithm only uses stochastic gradient descent (not batched) and constant learning rate without momentum, I have found that the best performance is given by 

	ALPHA = 0.003;
	NUM_HU = 10;
	NUM_EP = 100;
	
This yields a peak of 85.40% success rate.
An already trained Neural Net can be found serialized in the file NeuralNet.ser

#Candidate Elimination Algorithm

The Design and algorithm in Prolog can be found in the folder Task3.

An invalid object is said to be one that is heavy and made from metal.

The file file.txt provides a training file containing a number of candidates and whether they are accepted or not. 
		

	
	
