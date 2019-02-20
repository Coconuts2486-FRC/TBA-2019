import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
		
		//DeepNetworkAbilities.GenerateClassificationNet(40, 40, 50, 2);
		//JSON_Parsing.WriteToFile(HTTP.events(), "/Users/logan42474/Desktop/aaaaaaaaaaaaa.txt");
		
		
		GameData.teamkeys=(ArrayList<String>) HTTP.getTeamKeys(GameData.year+GameData.event);
		//System.out.print(HTTP.getTeamKeys(GameData.year+GameData.event));
		GameData.setMatchData();
		JSON_Parsing.WriteToFile(JSONGenerators.getAllMatchData(), "/Users/logan42474/Desktop/Match Data.txt");
		CSVWriter.WriteGameData("/Users/logan42474/Desktop/Testthing.csv");
		
		
		
		/*
		//System.out.println(GameData.matchdata.get(GameData.teamkeys.get(0)).get(0).blueData.score);
		for(int i = 0;i<GameData.teamkeys.size();i++) {
			
		}
		JSON_Parsing.WriteToFile(new GsonBuilder().setPrettyPrinting().create().toJson(GameData.matchdata).toString(), "/Users/logan42474/Desktop/Match Data.txt");
		
		
		
		
		// Simple DeepLearning Model
		/*
		DeepNetworkAbilities.GenerateClassificationNet(2, 1, 3, 1);
		
		DeepNetworkAbilities.Train(10000, 0.01, "/Users/logan42474/Desktop/testtext.csv", 2, 1, false);
		
		System.out.println(DeepNetworkAbilities.calculate(new double[] {0,0}));
		System.out.println(DeepNetworkAbilities.calculate(new double[] {0,6}));
		System.out.println(DeepNetworkAbilities.calculate(new double[] {6,0}));
		System.out.println(DeepNetworkAbilities.calculate(new double[] {6,6}));
		*/
		
		/*
		GameData.teamkeys=JSON_Parsing.teamkeys(HTTP.getTeamKeys(year+event));
		System.out.println(GameData.teamkeys);
		for(int i = 0; i<GameData.teamkeys.size();i++) {
			double round = i;
			double length = GameData.teamkeys.size();
			GameData.teammatchdata.put(GameData.teamkeys.get(i), HTTP.matches(GameData.teamkeys.get(i)));
			int per = (int) ((round/length)*100);
			System.out.println("% "+per);
		}
		for(int i = 0;i<GameData.teamkeys.size();i++) {
			GameData.scores.put(GameData.teamkeys.get(i), JSON_Parsing.teamMatchScore(GameData.teamkeys.get(i), GameData.teammatchdata.get(GameData.teamkeys.get(i))));
		}
		System.out.println(GameData.scores.toString());
		JSON_Parsing.WriteToFile(GameData.teammatchdata.toString(), "/Users/logan42474/Desktop/AllMatchData.txt");
		JSON_Parsing.WriteToFile(GameData.scores.toString(), "/Users/logan42474/Desktop/MatchScores.txt");
		*/
	}

}