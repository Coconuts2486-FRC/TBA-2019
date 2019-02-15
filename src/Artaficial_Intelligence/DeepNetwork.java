package Artaficial_Intelligence;

import java.io.FileNotFoundException;

import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration.ListBuilder;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer.Builder;


public class DeepNetwork {
public static MultiLayerNetwork net;
public static void Train(int trainingItterations, Double minimalError, String filePath, int inputs, int outputs, boolean hasHeader) throws FileNotFoundException {
	DataSet ds = DataTransformer.ArrayListToDataSet(filePath, inputs, outputs, hasHeader);
	net.setListeners(new ScoreIterationListener(10));
	if(minimalError==null) {
		for(int i = 0; i<trainingItterations; i++) {
			net.fit(ds);
		}
	}if(minimalError>0.0){
		net.fit(ds);
		while(net.score()>=minimalError) {
			net.fit(ds);
		}
	}
}
public static void GenerateNet(int numberOfInputs, int numberOfOutputs, int numberOfHiddenNodes, int numberOfHiddenLayers) {
	NeuralNetConfiguration.Builder builder = new NeuralNetConfiguration.Builder();
	builder.updater(new Sgd(0.1));
	builder.seed(2486);
	
	 ListBuilder listBuilder = builder.list();
	 DenseLayer.Builder InputLayerBuilder = new DenseLayer.Builder();
	 InputLayerBuilder.nIn(numberOfInputs);
	 InputLayerBuilder.nOut(numberOfHiddenNodes);
	 InputLayerBuilder.activation(Activation.SIGMOID);
	 InputLayerBuilder.weightInit(WeightInit.SIGMOID_UNIFORM);
	 
	 listBuilder.layer(0, InputLayerBuilder.build());
	 
	 for(int i = 1;i<=numberOfHiddenLayers;i++) {
		 DenseLayer.Builder HiddenLayerBuilder = new DenseLayer.Builder();
		 HiddenLayerBuilder.nIn(numberOfHiddenNodes);
		 HiddenLayerBuilder.nOut(numberOfHiddenNodes);
		 HiddenLayerBuilder.activation(Activation.SIGMOID);
		 HiddenLayerBuilder.weightInit(WeightInit.SIGMOID_UNIFORM);
		 listBuilder.layer(i, HiddenLayerBuilder.build());
	 }
	 
	 Builder OutputLayerBuilder = new OutputLayer.Builder(LossFunctions.LossFunction.XENT);
	 OutputLayerBuilder.nIn(numberOfHiddenNodes);
	 OutputLayerBuilder.nOut(numberOfOutputs);
	 OutputLayerBuilder.activation(Activation.SIGMOID);
	 OutputLayerBuilder.weightInit(WeightInit.SIGMOID_UNIFORM);
	 listBuilder.layer(numberOfHiddenLayers+1, OutputLayerBuilder.build());
	 
	 MultiLayerConfiguration configure = listBuilder.build();
     MultiLayerNetwork neto = new MultiLayerNetwork(configure);
	 neto.init();
	 net = neto;
}
}
