import java.util.ArrayList;

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
		
		
		
		GameData.teamkeys=(ArrayList<String>) HTTP.getTeamKeys(GameData.year+GameData.event);
		//System.out.print(HTTP.getTeamKeys(GameData.year+GameData.event));
		System.out.println(GameData.teamkeys);
		//JSON_Parsing.WriteToFile(JSONGenerators.getAllMatchData().toString(), "/Users/logan42474/Desktop/testarraything.txt");
		
		
		
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