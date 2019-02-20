package Artificial_Intelligence;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration.ListBuilder;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer.Builder;


public class DeepNetworkAbilities {
public static MultiLayerNetwork net;
public static void saveModel(String filepath) throws IOException {
        ModelSerializer.writeModel(net, filepath, true);
}
public static void loadModel(String filepath) throws IOException {
	net = ModelSerializer.restoreMultiLayerNetwork(filepath);
    //System.out.println("Saved and loaded parameters are equal:      " + net.params().equals(restored.params()));
    //System.out.println("Saved and loaded configurations are equal:  " + net.getLayerWiseConfigurations().equals(restored.getLayerWiseConfigurations()));
}
public static INDArray calculate(double[] input){
	INDArray in = Nd4j.zeros(1, net.layerInputSize(0));
	for(int i = 0;i<input.length;i++) {
		in.putScalar(new int[]{0, i},input[i]);
	}
	return net.output(in);
}
public static void Train(int trainingItterations, Double minimalError, String filePath, int inputs, int outputs, boolean hasHeader) throws FileNotFoundException {
	DataSet ds = DataTransformer.ArrayListToDataSet(filePath, inputs, outputs, hasHeader);
	net.setListeners(new ScoreIterationListener(500));
	if(minimalError==null) {
		for(int i = 0; i<trainingItterations; i++) {
			net.fit(ds);
		}
	}else{
		net.fit(ds);
		while(net.score()>=minimalError) {
			net.fit(ds);
		}
	}
}
public static void GenerateClassificationNet(int numberOfInputs, int numberOfOutputs, int numberOfHiddenNodes, int numberOfHiddenLayers) {
	NeuralNetConfiguration.Builder builder = new NeuralNetConfiguration.Builder();
	builder.updater(new Sgd(0.1));
	builder.seed(2486);
	
	 ListBuilder listBuilder = builder.list();
	 DenseLayer.Builder InputLayerBuilder = new DenseLayer.Builder();
	 InputLayerBuilder.nIn(numberOfInputs);
	 InputLayerBuilder.nOut(numberOfHiddenNodes);
	 InputLayerBuilder.activation(Activation.LEAKYRELU);
	 InputLayerBuilder.weightInit(WeightInit.XAVIER);
	 
	 listBuilder.layer(0, InputLayerBuilder.build());
	 
	 for(int i = 1;i<=numberOfHiddenLayers;i++) {
		 DenseLayer.Builder HiddenLayerBuilder = new DenseLayer.Builder();
		 HiddenLayerBuilder.nIn(numberOfHiddenNodes);
		 HiddenLayerBuilder.nOut(numberOfHiddenNodes);
		 HiddenLayerBuilder.activation(Activation.SIGMOID);
		 HiddenLayerBuilder.weightInit(WeightInit.XAVIER);
		 listBuilder.layer(i, HiddenLayerBuilder.build());
	 }
	 
	 Builder OutputLayerBuilder = new OutputLayer.Builder(LossFunctions.LossFunction.XENT);
	 OutputLayerBuilder.nIn(numberOfHiddenNodes);
	 OutputLayerBuilder.nOut(numberOfOutputs);
	 OutputLayerBuilder.activation(Activation.SIGMOID);
	 OutputLayerBuilder.weightInit(WeightInit.XAVIER);
	 listBuilder.layer(numberOfHiddenLayers+1, OutputLayerBuilder.build());
	 listBuilder.backprop(true);
	 
	 MultiLayerConfiguration configure = listBuilder.build();
     MultiLayerNetwork neto = new MultiLayerNetwork(configure);
	 neto.init();
	 net = neto;
}
}