package Artificial_Intelligence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.deeplearning4j.nn.api.OptimizationAlgorithm;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration.ListBuilder;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer.Builder;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.deeplearning4j.util.ModelSerializer;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.learning.config.Nesterovs;
import org.nd4j.linalg.learning.config.Sgd;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import Data.GameData;

public class DeepNetworkAverage {
	public static MultiLayerNetwork net;
	public static boolean training = false;
	public static void saveModel(String filepath) throws IOException {
        ModelSerializer.writeModel(net, filepath, true);
}
public static void loadModel(String filepath) throws IOException {
	net = ModelSerializer.restoreMultiLayerNetwork(filepath);
}
public static void Train(int trainingItterations, Double minimalError, String filePath, boolean hasHeader) throws FileNotFoundException {
	DataSet ds = DataTransformer.ArrayListToDataSet(filePath, hasHeader);
	net.setListeners(new ScoreIterationListener(500));
	if(minimalError==null) {
		for(int i = 0; i<trainingItterations; i++) {
			net.fit(ds);
		}
	}else{
		net.fit(ds);
		while(Math.sqrt(net.score()*net.score())>minimalError&&training) {
			net.fit(ds);
		}
	}
}
public static INDArray calculate(String teamkey){
	INDArray in = Nd4j.zeros(1, 100);
	ArrayList<Double> inputs = new ArrayList<Double>();
	int len = GameData.matchdata.get(teamkey).matches.size();
	for(int ie = len-5; ie<len;ie++) {
		if(GameData.matchdata.get(teamkey).matches.get(ie).alliance.equals("blue")) {
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).blueData.bay1));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).blueData.bay2));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).blueData.bay3));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).blueData.bay4));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).blueData.bay5));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).blueData.bay6));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).blueData.bay7));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).blueData.bay8));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).blueData.lowLeftRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).blueData.lowLeftRocketNear));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).blueData.lowRightRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).blueData.lowRightRocketNear));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).blueData.midLeftRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).blueData.midLeftRocketNear));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).blueData.midRightRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).blueData.midRightRocketNear));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).blueData.topLeftRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).blueData.topLeftRocketNear));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).blueData.topRightRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).blueData.topRightRocketNear));
			
		}else {
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).redData.bay1));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).redData.bay2));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).redData.bay3));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).redData.bay4));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).redData.bay5));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).redData.bay6));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).redData.bay7));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).redData.bay8));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).redData.lowLeftRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).redData.lowLeftRocketNear));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).redData.lowRightRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).redData.lowRightRocketNear));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).redData.midLeftRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).redData.midLeftRocketNear));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).redData.midRightRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).redData.midRightRocketNear));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).redData.topLeftRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).redData.topLeftRocketNear));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).redData.topRightRocketFar));
			inputs.add(DataTransformer.LabelToDouble(GameData.matchdata.get(teamkey).matches.get(ie).redData.topRightRocketNear));
		}
	}
	for(int i = 0;i<100;i++) {
		in.putScalar(new int[]{0, i},inputs.get(i));
	}
	INDArray output = net.output(in);
	return output;
}
	public static void GenerateAverageNet(int numberOfInputs, int numberOfOutputs, int numberOfHiddenNodes, int numberOfHiddenLayers) {
		NeuralNetConfiguration.Builder builder = new NeuralNetConfiguration.Builder();
		builder.seed(2486);
		builder.miniBatch(false);
		builder.optimizationAlgo(OptimizationAlgorithm.STOCHASTIC_GRADIENT_DESCENT);
		
		
		ListBuilder listBuilder = builder.list();
		
		DenseLayer.Builder InputLayerBuilder = new DenseLayer.Builder();
		 InputLayerBuilder.nIn(numberOfInputs);
		 InputLayerBuilder.nOut(numberOfHiddenNodes);
		 InputLayerBuilder.activation(Activation.RELU);
		 InputLayerBuilder.weightInit(WeightInit.XAVIER_UNIFORM);
		 listBuilder.layer(0, InputLayerBuilder.build());
		 
		 for(int i = 1;i<=numberOfHiddenLayers;i++) {
			 DenseLayer.Builder HiddenLayerBuilder = new DenseLayer.Builder();
			 HiddenLayerBuilder.nIn(numberOfHiddenNodes);
			 HiddenLayerBuilder.nOut(numberOfHiddenNodes);
			 HiddenLayerBuilder.activation(Activation.RELU);
			 HiddenLayerBuilder.weightInit(WeightInit.XAVIER_UNIFORM);
			 listBuilder.layer(i, HiddenLayerBuilder.build());
		 }
		 
		 Builder OutputLayerBuilder = new OutputLayer.Builder(LossFunctions.LossFunction.MEAN_SQUARED_LOGARITHMIC_ERROR);
		 OutputLayerBuilder.nIn(numberOfHiddenNodes);
		 OutputLayerBuilder.nOut(numberOfOutputs);
		 OutputLayerBuilder.activation(Activation.RELU);
		 OutputLayerBuilder.weightInit(WeightInit.XAVIER_UNIFORM);
		 OutputLayerBuilder.updater(new Adam(1e-3)).biasInit(1e-3).biasUpdater(new Adam(1e-3*2)).build();
		 listBuilder.layer(numberOfHiddenLayers+1, OutputLayerBuilder.build());
		 listBuilder.backprop(true);
		 
		 MultiLayerConfiguration configure = listBuilder.build();
	     MultiLayerNetwork neto = new MultiLayerNetwork(configure);
		 neto.init();
		 net = neto;
	}
}
