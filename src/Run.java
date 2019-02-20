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
		GameData.teamkeys=(ArrayList<String>) HTTP.getTeamKeys(GameData.year+GameData.event);
		GameData.setMatchData();
		JSON_Parsing.WriteToFile(JSONGenerators.getAllMatchData(), "/Users/logan42474/Desktop/Match Data.txt");
		CSVWriter.WriteGameData("/Users/logan42474/Desktop/Testthing.csv");

	}

}