package Artificial_Intelligence;

import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration.ListBuilder;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer.Builder;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;

public class DeepNetworkScore {
	public static MultiLayerNetwork net;
	public static boolean training = false;
	public static void GenerateScoringNet(int numberOfInputs, int numberOfOutputs, int numberOfHiddenNodes, int numberOfHiddenLayers) {
		NeuralNetConfiguration.Builder builder = new NeuralNetConfiguration.Builder();
		builder.updater(new Sgd(0.1));
		builder.seed(2486);
		builder.biasInit(0);
		builder.miniBatch(false);
		
		ListBuilder listBuilder = builder.list();
		listBuilder.backprop(true);
		listBuilder.pretrain(false);
		
		DenseLayer.Builder InputLayerBuilder = new DenseLayer.Builder();
		 InputLayerBuilder.nIn(numberOfInputs);
		 InputLayerBuilder.nOut(numberOfHiddenNodes);
		 InputLayerBuilder.activation(Activation.RELU);
		 InputLayerBuilder.weightInit(WeightInit.RELU_UNIFORM);
		 listBuilder.layer(0, InputLayerBuilder.build());
		 
		 for(int i = 1;i<=numberOfHiddenLayers;i++) {
			 DenseLayer.Builder HiddenLayerBuilder = new DenseLayer.Builder();
			 HiddenLayerBuilder.nIn(numberOfHiddenNodes);
			 HiddenLayerBuilder.nOut(numberOfHiddenNodes);
			 HiddenLayerBuilder.activation(Activation.RELU);
			 HiddenLayerBuilder.weightInit(WeightInit.RELU_UNIFORM);
			 listBuilder.layer(i, HiddenLayerBuilder.build());
		 }
		 
		 Builder OutputLayerBuilder = new OutputLayer.Builder(LossFunctions.LossFunction.XENT);
		 OutputLayerBuilder.nIn(numberOfHiddenNodes);
		 OutputLayerBuilder.nOut(numberOfOutputs);
		 OutputLayerBuilder.activation(Activation.RELU);
		 OutputLayerBuilder.weightInit(WeightInit.RELU_UNIFORM);
		 listBuilder.layer(numberOfHiddenLayers+1, OutputLayerBuilder.build());
	}
}
