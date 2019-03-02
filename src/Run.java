import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.JsonSyntaxException;

import Artificial_Intelligence.DeepNetworkAbilities;
import CSV.CSVWriter;
import Data.GameData;
import GUI.MainGUI;
import Internet.HTTP;
import Internet.PingTBA;
import JSONing.JSONGenerators;
import JSONing.JSON_Parsing;

public class Run {
	//2019azfl Flagstaff
	//2019cave Ventura
	//team/frc2486/matches/2019
	static String basedir = System.getProperty("user.home")+"/Desktop/FRC 2019 Data/";
	public static void main(String[] args) throws Exception {
		File baseFile = new File(basedir);
		System.out.println(baseFile.exists());
		baseFile.mkdir();
		System.out.println(baseFile.exists());
		//MainGUI GUI = new MainGUI();
		//PingTBA ping = new PingTBA();
		//GUI.Ping=ping.ping();
		GameData.teamkeys=(ArrayList<String>) HTTP.getTeamKeys(GameData.year+GameData.event);
		
		//GameData.setMatchData();
		//JSON_Parsing.WriteToFile(JSONGenerators.getAllMatchData(), basedir+"Match Data.txt");
		//CSVWriter.WriteGameData(basedir+"Match Data.csv");
		
		GameData.uploadMatchData(basedir+"Match Data.txt");
		DeepNetworkAbilities.loadModel(basedir+"DeepNetwork.zip");
		//DeepNetworkAbilities.GenerateClassificationNet(100, 8, 80, 3);
		//DeepNetworkAbilities.saveModel(basedir+"DeepNetwork.zip");
		
		DeepNetworkAbilities.Train(10000, null, "/Users/logan42474/Desktop/2019 DeepNetwork Training .csv", true);
		DeepNetworkAbilities.saveModel(basedir+"DeepNetwork.zip");
		System.out.println(DeepNetworkAbilities.calculate("frc1073"));
		System.out.println(DeepNetworkAbilities.calculate("frc1153"));
		System.out.println("Done");

		/*
		System.out.println(DeepNetworkAbilities.calculate(new double[] {0,0}));
		System.out.println(DeepNetworkAbilities.calculate(new double[] {0,6}));
		System.out.println(DeepNetworkAbilities.calculate(new double[] {6,0}));
		System.out.println(DeepNetworkAbilities.calculate(new double[] {6,6}));
		*/
	}

}