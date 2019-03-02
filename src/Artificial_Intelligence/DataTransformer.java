package Artificial_Intelligence;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;

import Data.ExampleStructure;
import Data.GameData;

public class DataTransformer {
	public static Double LabelToDouble(String input) {
		switch(input) {
		case "None":
			return 0.0;
		case "Panel":
			return 1.0;
		case "PanelAndCargo":
			return 2.0;
		default:
			return 0.0;
		}
		
	}
	public static DataSet ArrayListToDataSet(String filePath,boolean hasHeader) throws FileNotFoundException {
		HashMap<String,ArrayList<Double>> classifiers = CSVtoArrayList(filePath, hasHeader);
		ArrayList<ExampleStructure> data = new ArrayList<ExampleStructure>();
		Object[] keys = classifiers.keySet().toArray();
		for(int i = 0;i<classifiers.size();i++) {
			if(GameData.matchdata.get(keys[i]).size()>4) {
				ExampleStructure es = new ExampleStructure();
				for(int ie = 0; ie<5;ie++) {
					if(GameData.matchdata.get(keys[i]).get(ie).alliance.equals("blue")) {
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).blueData.bay1));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).blueData.bay2));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).blueData.bay3));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).blueData.bay4));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).blueData.bay5));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).blueData.bay6));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).blueData.bay7));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).blueData.bay8));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).blueData.lowLeftRocketFar));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).blueData.lowLeftRocketNear));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).blueData.lowRightRocketFar));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).blueData.lowRightRocketNear));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).blueData.midLeftRocketFar));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).blueData.midLeftRocketNear));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).blueData.midRightRocketFar));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).blueData.midRightRocketNear));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).blueData.topLeftRocketFar));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).blueData.topLeftRocketNear));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).blueData.topRightRocketFar));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).blueData.topRightRocketNear));
						
					}else {
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).redData.bay1));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).redData.bay2));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).redData.bay3));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).redData.bay4));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).redData.bay5));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).redData.bay6));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).redData.bay7));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).redData.bay8));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).redData.lowLeftRocketFar));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).redData.lowLeftRocketNear));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).redData.lowRightRocketFar));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).redData.lowRightRocketNear));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).redData.midLeftRocketFar));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).redData.midLeftRocketNear));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).redData.midRightRocketFar));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).redData.midRightRocketNear));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).redData.topLeftRocketFar));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).redData.topLeftRocketNear));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).redData.topRightRocketFar));
						es.inputs.add(LabelToDouble(GameData.matchdata.get(keys[i]).get(ie).redData.topRightRocketNear));
					}
				}
				for(int ie = 0; ie<8;ie++) {
					es.outputs.add(classifiers.get(keys[i]).get(ie));
				}
				data.add(es);
			}else {
				
			}
		}
		INDArray input = Nd4j.zeros(data.size(), 100);
		//System.out.println(data.size()+"   "+inputs);
		INDArray labels = Nd4j.zeros(data.size(), 8);
		for(int i = 0; i<data.size();i++) {
			for(int ie = 0;ie<100;ie++) {
				input.putScalar(new int[]{i, ie}, data.get(i).inputs.get(ie));
			}
			for(int ie = 0;ie<8;ie++) {
				labels.putScalar(new int[]{i, ie}, data.get(i).outputs.get(ie));
			}
		}
		DataSet ds = new DataSet(input,labels);
		return ds;
	}
	public static HashMap<String, ArrayList<Double>> CSVtoArrayList(String filePath,boolean hasHeader) throws FileNotFoundException {
		File file = new File(filePath); 
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file); 
		ArrayList<String> layers = new ArrayList<String>();
		HashMap<String,ArrayList<Double>> out = new HashMap<String,ArrayList<Double>>();
		
		
		
		while(sc.hasNextLine()) {
			String data = sc.nextLine();
			layers.add(data);
		}
		
			layers.remove(0);
			
		for(int i = 0;i<layers.size();i++) {
			String[] items;
			items=layers.get(i).split(",");
			ArrayList<Double> value = new ArrayList<Double>();
			for(int ie = 1;ie<items.length;ie++) {
				value.add(Double.parseDouble(items[ie]));
			}
			
			out.put("frc"+items[0], value);
		}
		return out;
	}
}
