package Artificial_Intelligence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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
import org.nd4j.linalg.learning.config.Nesterovs;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import Data.GameData;

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
public static INDArray calculate(String teamkey){
	INDArray in = Nd4j.zeros(1, 100);
	ArrayList<Double> inputs = new ArrayList<Double>();
	int len = GameData.matchdata.get(teamkey).size();
	for(int ie = len-5; ie<len;ie++) {
		if(GameData.matchdata.get(teamkey).get(ie).alliance.equals("blue")) {
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).blueData.bay1));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).blueData.bay2));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).blueData.bay3));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).blueData.bay4));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).blueData.bay5));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).blueData.bay6));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).blueData.bay7));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).blueData.bay8));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).blueData.lowLeftRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).blueData.lowLeftRocketNear));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).blueData.lowRightRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).blueData.lowRightRocketNear));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).blueData.midLeftRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).blueData.midLeftRocketNear));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).blueData.midRightRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).blueData.midRightRocketNear));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).blueData.topLeftRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).blueData.topLeftRocketNear));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).blueData.topRightRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).blueData.topRightRocketNear));
			
		}else {
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).redData.bay1));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).redData.bay2));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).redData.bay3));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).redData.bay4));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).redData.bay5));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).redData.bay6));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).redData.bay7));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).redData.bay8));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).redData.lowLeftRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).redData.lowLeftRocketNear));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).redData.lowRightRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).redData.lowRightRocketNear));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).redData.midLeftRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).redData.midLeftRocketNear));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).redData.midRightRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).redData.midRightRocketNear));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).redData.topLeftRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).redData.topLeftRocketNear));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).redData.topRightRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).get(ie).redData.topRightRocketNear));
		}
	}
	for(int i = 0;i<100;i++) {
		in.putScalar(new int[]{0, i},inputs.get(i));
	}
	return net.output(in);
}
public static void Train(int trainingItterations, Double minimalError, String filePath, boolean hasHeader) throws FileNotFoundException {
	DataSet ds = DataTransformer.ArrayListToDataSet(filePath, hasHeader);
	net.setListeners(new ScoreIterationListener(1000));
	if(minimalError==null) {
		for(int i = 0; i<trainingItterations; i++) {
			net.fit(ds);
		}
	}else{
		net.fit(ds);
		while(net.score()>minimalError) {
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
	 listBuilder.backprop(true);
	 OutputLayerBuilder.updater(new Nesterovs(0.1));
	 
	 MultiLayerConfiguration configure = listBuilder.build();
     MultiLayerNetwork neto = new MultiLayerNetwork(configure);
	 neto.init();
	 net = neto;
}
}