import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Artificial_Intelligence.DeepNetworkAbilities;
import CSV.CSVWriter;
import Data.GameData;
import Internet.HTTP;
import JSONing.JSONGenerators;
import JSONing.JSON_Parsing;

public class Run {
	//2019azfl Flagstaff
	//2019cave Ventura
	//team/frc2486/matches/2019
	
	public static void main(String[] args) throws Exception {
		//GameData.teamkeys=(ArrayList<String>) HTTP.getTeamKeys(GameData.year+GameData.event);
		//GameData.uploadMatchData("/Users/logan42474/Desktop/Match Data.txt");
		//System.out.println(GameData.matchdata.get("frc246").get(0).blueData.score);
		//GameData.setMatchData();
		//JSON_Parsing.WriteToFile(JSONGenerators.getAllMatchData(), "/Users/logan42474/Desktop/Match Data.txt");
		//CSVWriter.WriteGameData("/Users/logan42474/Desktop/Match Data.csv");
		DeepNetworkAbilities.GenerateClassificationNet(2, 1, 3, 1);
		DeepNetworkAbilities.Train(10000, 0.01, "/Users/logan42474/Desktop/testtext.csv", 2, 1, false);
		DeepNetworkAbilities.saveModel("/Users/logan42474/Desktop/DeepNetwork.txt");
		DeepNetworkAbilities.loadModel("/Users/logan42474/Desktop/DeepNetwork.txt");
		
		System.out.println(DeepNetworkAbilities.calculate(new double[] {0,0}));
		System.out.println(DeepNetworkAbilities.calculate(new double[] {0,6}));
		System.out.println(DeepNetworkAbilities.calculate(new double[] {6,0}));
		System.out.println(DeepNetworkAbilities.calculate(new double[] {6,6}));

	}

}