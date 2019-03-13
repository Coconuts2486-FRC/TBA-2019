package Artificial_Intelligence;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import Data.ExampleStructure;
import Data.GameData;
import Data.GameDataStructure;
import Internet.HTTP;
import JSONing.JSONGenerators;
import JSONing.JSON_Parsing;

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
	public static HashMap<String,ArrayList<GameDataStructure>> setMatchData(Object[] keys) throws JsonSyntaxException, Exception {
		HashMap<String,ArrayList<GameDataStructure>> AllOut = new HashMap<String,ArrayList<GameDataStructure>>();
		for(int i = 0;i<keys.length;i++) {
			ArrayList<GameDataStructure> output = new ArrayList<GameDataStructure>();
			JsonParser jsonParser = new JsonParser();
			JsonArray jArray = jsonParser.parse(HTTP.matches(keys[i].toString())).getAsJsonArray();
			
			for(int ie = 0;ie<jArray.size();ie++) {
				try {
				GameDataStructure GDS = new GameDataStructure();
				JsonObject jObject = jArray.get(ie).getAsJsonObject();
				JsonObject baseblue = jObject.get("alliances").getAsJsonObject().get("blue").getAsJsonObject();
				JsonObject baseRed = jObject.get("alliances").getAsJsonObject().get("red").getAsJsonObject();
				String preListblue = baseblue.get("team_keys").getAsJsonArray().toString().replace("[", "").replace("]", "").replace("\"", "");
				String preListRed = baseRed.get("team_keys").getAsJsonArray().toString().replace("[", "").replace("]", "").replace("\"", "");
				ArrayList<String> listblue = new ArrayList<String>(Arrays.asList(preListblue.split(",")));
				ArrayList<String> listRed = new ArrayList<String>(Arrays.asList(preListRed.split(",")));
				if(listblue.contains(keys[i].toString())) {
					GDS.alliance="blue";
				}
				if(listRed.contains(keys[i].toString())) {
					GDS.alliance="red";
				}
				//Setting Specific Data
				
				//blue
				
				JsonObject blue = jObject.get("score_breakdown").getAsJsonObject().get("blue").getAsJsonObject();
				
				GDS.blueData.score=baseblue.get("score").getAsDouble();
				GDS.matchNumber=jObject.get("match_number").getAsInt();
				GDS.blueData.teamkey1=listblue.get(0);
				GDS.blueData.teamkey2=listblue.get(1);
				GDS.blueData.teamkey3=listblue.get(2);
				
				GDS.blueData.autoScore=blue.get("autoPoints").getAsDouble();
				
				GDS.blueData.teleopPoints=blue.get("teleopPoints").getAsDouble();
				
				GDS.blueData.bay1=blue.get("bay1").getAsString();
				GDS.blueData.bay2=blue.get("bay2").getAsString();
				GDS.blueData.bay3=blue.get("bay3").getAsString();
				GDS.blueData.bay4=blue.get("bay4").getAsString();
				GDS.blueData.bay5=blue.get("bay5").getAsString();
				GDS.blueData.bay6=blue.get("bay6").getAsString();
				GDS.blueData.bay7=blue.get("bay7").getAsString();
				GDS.blueData.bay8=blue.get("bay8").getAsString();
				
				GDS.blueData.cargoPoints=blue.get("cargoPoints").getAsDouble();
				
				GDS.blueData.completeRocketRankingPoint=blue.get("completeRocketRankingPoint").getAsBoolean();
				
				GDS.blueData.endgameRobot1=blue.get("endgameRobot1").getAsString();
				GDS.blueData.endgameRobot2=blue.get("endgameRobot2").getAsString();
				GDS.blueData.endgameRobot3=blue.get("endgameRobot3").getAsString();
				
				GDS.blueData.foulCount=blue.get("foulCount").getAsDouble();
				GDS.blueData.techFoulCount=blue.get("techFoulCount").getAsDouble();
				
				GDS.blueData.habLineRobot1=blue.get("habLineRobot1").getAsString();
				GDS.blueData.habLineRobot2=blue.get("habLineRobot2").getAsString();
				GDS.blueData.habLineRobot3=blue.get("habLineRobot3").getAsString();
				
				GDS.blueData.lowLeftRocketFar=blue.get("lowLeftRocketFar").getAsString();
				GDS.blueData.lowLeftRocketNear=blue.get("lowLeftRocketNear").getAsString();
				GDS.blueData.lowRightRocketFar=blue.get("lowRightRocketFar").getAsString();
				GDS.blueData.lowRightRocketNear=blue.get("lowRightRocketNear").getAsString();

				GDS.blueData.midLeftRocketFar=blue.get("midLeftRocketFar").getAsString();
				GDS.blueData.midLeftRocketNear=blue.get("midLeftRocketNear").getAsString();
				GDS.blueData.midRightRocketFar=blue.get("midRightRocketFar").getAsString();
				GDS.blueData.midRightRocketNear=blue.get("midRightRocketNear").getAsString();
				
				GDS.blueData.topLeftRocketFar=blue.get("topLeftRocketFar").getAsString();
				GDS.blueData.topLeftRocketNear=blue.get("topLeftRocketNear").getAsString();
				GDS.blueData.topRightRocketFar=blue.get("topRightRocketFar").getAsString();
				GDS.blueData.topRightRocketNear=blue.get("topRightRocketNear").getAsString();
				
				GDS.blueData.preMatchLevelRobot1=blue.get("preMatchLevelRobot1").getAsString();
				GDS.blueData.preMatchLevelRobot2=blue.get("preMatchLevelRobot2").getAsString();
				GDS.blueData.preMatchLevelRobot3=blue.get("preMatchLevelRobot3").getAsString();
				
				//red
				JsonObject red = jObject.get("score_breakdown").getAsJsonObject().get("red").getAsJsonObject();
				GDS.redData.score=baseRed.get("score").getAsDouble();
				GDS.redData.teamkey1=listRed.get(0);
				GDS.redData.teamkey2=listRed.get(1);
				GDS.redData.teamkey3=listRed.get(2);
				
				GDS.redData.autoScore=red.get("autoPoints").getAsDouble();
				GDS.redData.teleopPoints=red.get("teleopPoints").getAsDouble();
				
				GDS.redData.bay1=red.get("bay1").getAsString();
				GDS.redData.bay2=red.get("bay2").getAsString();
				GDS.redData.bay3=red.get("bay3").getAsString();
				GDS.redData.bay4=red.get("bay4").getAsString();
				GDS.redData.bay5=red.get("bay5").getAsString();
				GDS.redData.bay6=red.get("bay6").getAsString();
				GDS.redData.bay7=red.get("bay7").getAsString();
				GDS.redData.bay8=red.get("bay8").getAsString();
				
				GDS.redData.cargoPoints=red.get("cargoPoints").getAsDouble();
				
				GDS.redData.completeRocketRankingPoint=red.get("completeRocketRankingPoint").getAsBoolean();
				
				GDS.redData.endgameRobot1=red.get("endgameRobot1").getAsString();
				GDS.redData.endgameRobot2=red.get("endgameRobot2").getAsString();
				GDS.redData.endgameRobot3=red.get("endgameRobot3").getAsString();
				
				GDS.redData.foulCount=red.get("foulCount").getAsDouble();
				GDS.redData.techFoulCount=red.get("techFoulCount").getAsDouble();
				
				GDS.redData.habLineRobot1=red.get("habLineRobot1").getAsString();
				GDS.redData.habLineRobot2=red.get("habLineRobot2").getAsString();
				GDS.redData.habLineRobot3=red.get("habLineRobot3").getAsString();
				
				GDS.redData.lowLeftRocketFar=red.get("lowLeftRocketFar").getAsString();
				GDS.redData.lowLeftRocketNear=red.get("lowLeftRocketNear").getAsString();
				GDS.redData.lowRightRocketFar=red.get("lowRightRocketFar").getAsString();
				GDS.redData.lowRightRocketNear=red.get("lowRightRocketNear").getAsString();

				GDS.redData.midLeftRocketFar=red.get("midLeftRocketFar").getAsString();
				GDS.redData.midLeftRocketNear=red.get("midLeftRocketNear").getAsString();
				GDS.redData.midRightRocketFar=red.get("midRightRocketFar").getAsString();
				GDS.redData.midRightRocketNear=red.get("midRightRocketNear").getAsString();
				
				GDS.redData.topLeftRocketFar=red.get("topLeftRocketFar").getAsString();
				GDS.redData.topLeftRocketNear=red.get("topLeftRocketNear").getAsString();
				GDS.redData.topRightRocketFar=red.get("topRightRocketFar").getAsString();
				GDS.redData.topRightRocketNear=red.get("topRightRocketNear").getAsString();
				
				GDS.redData.preMatchLevelRobot1=red.get("preMatchLevelRobot1").getAsString();
				GDS.redData.preMatchLevelRobot2=red.get("preMatchLevelRobot2").getAsString();
				GDS.redData.preMatchLevelRobot3=red.get("preMatchLevelRobot3").getAsString();
				
				output.add(GDS);
				}catch(Exception e) {
				}
			}
		
			AllOut.put(keys[i].toString(), output);
		}
		return AllOut;
	}
	public static DataSet ArrayListToDataSet(String filePath,boolean hasHeader) throws FileNotFoundException {
		HashMap<String,ArrayList<Double>> classifiers = CSVtoArrayList(filePath, hasHeader);
		ArrayList<ExampleStructure> data = new ArrayList<ExampleStructure>();
		Object[] keys = classifiers.keySet().toArray();
		JSON_Parsing.WriteToFile(new GsonBuilder().setPrettyPrinting().create().toJson(keys), "/Users/logan42474/Desktop/2019 DeepNetwork Training data stuff.txt");
		try {
			HashMap<String,ArrayList<GameDataStructure>> trainingGameData = setMatchData(keys);
		for(int i = 0;i<classifiers.size();i++) {
			//trainingGameData.put(key, value)
			if(trainingGameData.get(keys[i]).size()>4) {
				for(int z = 0;z<trainingGameData.get(keys[i]).size()-4;z++) {
					ExampleStructure es = new ExampleStructure();
				for(int ie = z; ie<5+z;ie++) {
					if(trainingGameData.get(keys[i]).get(ie).alliance.equals("blue")) {
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).blueData.bay1));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).blueData.bay2));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).blueData.bay3));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).blueData.bay4));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).blueData.bay5));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).blueData.bay6));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).blueData.bay7));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).blueData.bay8));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).blueData.lowLeftRocketFar));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).blueData.lowLeftRocketNear));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).blueData.lowRightRocketFar));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).blueData.lowRightRocketNear));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).blueData.midLeftRocketFar));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).blueData.midLeftRocketNear));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).blueData.midRightRocketFar));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).blueData.midRightRocketNear));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).blueData.topLeftRocketFar));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).blueData.topLeftRocketNear));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).blueData.topRightRocketFar));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).blueData.topRightRocketNear));
						
					}else {
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).redData.bay1));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).redData.bay2));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).redData.bay3));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).redData.bay4));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).redData.bay5));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).redData.bay6));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).redData.bay7));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).redData.bay8));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).redData.lowLeftRocketFar));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).redData.lowLeftRocketNear));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).redData.lowRightRocketFar));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).redData.lowRightRocketNear));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).redData.midLeftRocketFar));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).redData.midLeftRocketNear));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).redData.midRightRocketFar));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).redData.midRightRocketNear));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).redData.topLeftRocketFar));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).redData.topLeftRocketNear));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).redData.topRightRocketFar));
						es.inputs.add(LabelToDouble(trainingGameData.get(keys[i]).get(ie).redData.topRightRocketNear));
					}
				}
				for(int iee = 0; iee<8;iee++) {
					es.outputs.add(classifiers.get(keys[i]).get(iee));
				}
				data.add(es);
				}
			}else {
				
			}
		} }catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
