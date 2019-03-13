package Telegram;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.telegram.telegrambots.meta.api.objects.Update;

import com.google.gson.JsonSyntaxException;

import Artificial_Intelligence.DeepNetworkAbilities;
import CSV.CSVWriter;
import Data.GameData;
import Internet.HTTP;
import JSONing.JSONGenerators;
import JSONing.JSON_Parsing;

public class Commands {
	static String basedir = System.getProperty("user.home")+"/Desktop/FRC 2019 Data/";
public static String main(Update input){
	System.out.println(input.getMessage().getFrom().getUserName()+": "+input.getMessage().getFrom().getId()+": "+input.getMessage().getText());
	String[] formatted = format(input);
		String output = null;
		switch(formatted[0].toLowerCase()) {
		case "/stats": output=stats(formatted);
		break;
		case "/boot": output=boot(input,formatted);
		break;
		case "/startup": output=startup(input);
		break;
		case "/update": output=update(input);
		break;
		case "/start": output="To get statistics on teams use the command \n/stats"
				+ "\nExample: /stats 2486";
		break;
		default:  output="Error 404: Command Not Found";
		}
		return output;
}
public static String update(Update input) {
	MyAmazingBot MAB = new MyAmazingBot();
	switch(GameData.event) {
	case "azfl": MAB.errorMessage(input, "Updating Arizona North");
	break;
	case "cave": MAB.errorMessage(input, "Updating Ventura");
	break;
	case "caoc": MAB.errorMessage(input, "Updating Orange County");
	break;
	default: MAB.errorMessage(input, "Updating "+GameData.event);
	}
	try {
		GameData.setMatchData();
	} catch (JsonSyntaxException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	try {
		JSON_Parsing.WriteToFile(JSONGenerators.getAllMatchData(), basedir+"Match Data.txt");
		File csfile = new File(basedir+"Match Data.csv");
		csfile.delete();
		CSVWriter.WriteGameData(basedir+"Match Data.csv");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return "Database Updated!";
	
}
private static String[] format(Update input) {
	if(input.getMessage().getText().contains(" ")) {
		String[] output = input.getMessage().getText().split(" ");
		return output;
	}else {
		String[] output = {input.getMessage().getText()};
		return output;
	}
}
public static String startup(Update input) {
	if(input.getMessage().getFrom().getId()==796720243) {
		MyAmazingBot MAB = new MyAmazingBot();
		MAB.errorMessage(input, "Starting up...");
		try {
			GameData.uploadEventKey(basedir+"Event Key.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		switch(GameData.event) {
		case "azfl": MAB.errorMessage(input, "Starting up Arizona North");
		break;
		case "cave": MAB.errorMessage(input, "Starting up Ventura");
		break;
		case "caoc": MAB.errorMessage(input, "Starting up Orange County");
		break;
		default: MAB.errorMessage(input, "Starting up "+GameData.event);
		}
		MAB.errorMessage(input, "Uploadind Data...");
		try {
			GameData.uploadTeamKeys(basedir+"Team Keys.txt");
			GameData.uploadMatchData(basedir+"Match Data.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			MAB.errorMessage(input, e.toString());
		}
		MAB.errorMessage(input, "Uploading Neural Network...");
		try {
			DeepNetworkAbilities.loadModel(basedir+"DeepNetwork.zip");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			MAB.errorMessage(input, e.toString());
		}
		return "Startup Complete!";
	}else {
		return "--- You are not authorized to use this command ---";
	}
}
public static String boot(Update input, String[] tours){
	if(tours.length>1) {
	String tour = tours[1];
	if(input.getMessage().getFrom().getId()==796720243) {
		MyAmazingBot MAB = new MyAmazingBot();
		if(tour.equals("az")||tour.equals("azfl")||tour.equals("flag")||tour.equals("flagstaff")||tour.equals("arizona")||tour.equals("nau")) {
			GameData.event="azfl";
			MAB.errorMessage(input, "Tournament set to Arizona North");
		}
		if(tour.equals("ventura")||tour.equals("california")||tour.equals("cali")||tour.equals("cave")) {
			GameData.event="cave";
			MAB.errorMessage(input, "Tournament set to Ventura");
		}
		if(tour.equals("test")||tour.equals("orange")||tour.equals("practice")||tour.equals("default")) {
			GameData.event="caoc";
			MAB.errorMessage(input, "Tournament set to Orange County");
		}
		
		MAB.errorMessage(input, "Initializing Bootup...");
		MAB.errorMessage(input, "Downloading Data...");
		
		try {
			GameData.teamkeys=(ArrayList<String>) HTTP.getTeamKeys(GameData.year+GameData.event);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MAB.errorMessage(input, e.toString());
		}
		try {
			GameData.setMatchData();
		} catch (JsonSyntaxException e) {
			// TODO Auto-generated catch block
			MAB.errorMessage(input, e.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MAB.errorMessage(input, e.toString());
		}
		MAB.errorMessage(input, "Saving Data...");
		try {
			JSON_Parsing.WriteToFile(JSONGenerators.getAllTeamKeys(), basedir+"Team Keys.txt");
			JSON_Parsing.WriteToFile(JSONGenerators.getAllMatchData(), basedir+"Match Data.txt");
			JSON_Parsing.WriteToFile(JSONGenerators.getEventKey(), basedir+"Event Key.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MAB.errorMessage(input, e.toString());
		}
		try {
			File csfile = new File(basedir+"Match Data.csv");
			csfile.delete();
			CSVWriter.WriteGameData(basedir+"Match Data.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			MAB.errorMessage(input, e.toString());
		}
		File file = new File(basedir+"DeepNetwork.zip");
		if(!file.exists()) {
			try {
				MAB.errorMessage(input, "Building Neural Network...");
				DeepNetworkAbilities.GenerateClassificationNet(100, 8, 20, 3);
				DeepNetworkAbilities.saveModel(basedir+"DeepNetwork.zip");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else {
			MAB.errorMessage(input, "Loading Neural Network...");
			try {
				DeepNetworkAbilities.loadModel(basedir+"DeepNetwork.zip");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return "Bootup Complete!";
	}else {
		return "--- You are not authorized to use this command ---";
	}
	}else {
		return "Enter a reigonal."
				+ "\nExample: /boot flagstaff";
	}
}
public static String stats(String[] teamnumber) {
	if(teamnumber.length>1) {
	try {
		System.out.println(teamnumber[1]);
		System.out.println(GameData.matchdata.get("frc"+teamnumber[1]).size());
	String out = "Team: "+ teamnumber[1] +"\n Matches Played: "+GameData.matchdata.get("frc"+teamnumber[1]).size()
			+"\n Predicted Abilities:"
			+ "\n Rocket Hatch Low:  "+ round(DeepNetworkAbilities.calculate("frc"+teamnumber[1]).getDouble(0),5)
			+ "\n Rocket Hatch Mid:  "+ round(DeepNetworkAbilities.calculate("frc"+teamnumber[1]).getDouble(1),5)
			+ "\n Rocket Hatch High: "+ round(DeepNetworkAbilities.calculate("frc"+teamnumber[1]).getDouble(2),5)
			+ "\n Rocket Cargo Low:  "+ round(DeepNetworkAbilities.calculate("frc"+teamnumber[1]).getDouble(3),5)
			+ "\n Rocket Cargo Mid:  "+ round(DeepNetworkAbilities.calculate("frc"+teamnumber[1]).getDouble(4),5)
			+ "\n Rocket Cargo High: "+ round(DeepNetworkAbilities.calculate("frc"+teamnumber[1]).getDouble(5),5)
			+ "\n CargoShip Hatches: "+ round(DeepNetworkAbilities.calculate("frc"+teamnumber[1]).getDouble(6),5)
			+ "\n CargoShip Cargo:   "+ round(DeepNetworkAbilities.calculate("frc"+teamnumber[1]).getDouble(7),5);
	return out;
	}catch(Exception e) {
		return "There was an error while trying to retreive the data."
				+ " There is a possibility that the database has not been booted up yet."
				+ " The error in question is: "+e.toString();
	}
	}else {
		return "Make sure to enter a team number."
				+ "\nExample: /stats 2486";
	}
}
public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
}
}
