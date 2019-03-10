import java.io.File;
import java.util.ArrayList;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import Data.GameData;
import Data.Secretkeys;
import GUI.MainGUI;
import Internet.HTTP;
import Internet.PingTBA;
import Telegram.MyAmazingBot;

public class Run {
	//2019azfl Flagstaff
	//2019cave Ventura
	//team/frc2486/matches/2019
	static String basedir = System.getProperty("user.home")+"/Desktop/FRC 2019 Data/";
	public static void main(String[] args) throws Exception {
		// Startup
		
		File baseFile = new File(basedir);
		baseFile.mkdir();
		Secretkeys.startup();
		
		
		GameData.teamkeys=(ArrayList<String>) HTTP.getTeamKeys(GameData.year+GameData.event);
		//GUI Startup
		MainGUI GUI = new MainGUI();
		PingTBA ping = new PingTBA();
		GUI.Ping=ping.ping();
		GUI.run();
		
		//GameData.setMatchData();
		//JSON_Parsing.WriteToFile(JSONGenerators.getAllMatchData(), basedir+"Match Data.txt");
		//CSVWriter.WriteGameData(basedir+"Match Data.csv");
		
		//GameData.uploadMatchData(basedir+"Match Data.txt");
		//DeepNetworkAbilities.loadModel(basedir+"DeepNetwork.zip");
		//DeepNetworkAbilities.GenerateClassificationNet(100, 8, 80, 3);
		//DeepNetworkAbilities.saveModel(basedir+"DeepNetwork.zip");
		
		//DeepNetworkAbilities.Train(10000, null, "/Users/logan42474/Desktop/2019 DeepNetwork Training .csv", true);
		//DeepNetworkAbilities.saveModel(basedir+"DeepNetwork.zip");
		//CSVWriter.WritePredictedData(basedir+"Predicted Data.csv");
		//System.out.println("Done");
	}

}