package Telegram;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.nd4j.linalg.api.ndarray.INDArray;
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
		case "/train": output=train(input,formatted);
		break;
		case "/start": output="To get statistics on teams use the command \n/stats"
				+ "\nExample: /stats 2486";
		break;
		default:  output="Error 404: Command Not Found";
		}
		return output;
}
public static String update(Update input) {
	Thread thread = new Thread() {
		public void run() {
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
				CSVWriter cs = new CSVWriter();
				cs.WriteGameData(basedir+"Match Data.csv");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MAB.errorMessage(input, "Database Updated!");
		}
	};
	thread.start();
	return "Updating Database...";
	
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
	Thread thread = new Thread("Startup Thread") {
		public void run() {
			MyAmazingBot MAB = new MyAmazingBot();
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
			MAB.errorMessage(input, "Uploading Data...");
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
			MAB.errorMessage(input, "Startup Complete!");
		}
	};
	if(input.getMessage().getFrom().getId()==796720243) {
		thread.start();
		return "Starting up...";
	}else {
		return "--- You are not authorized to use this command ---";
	}
}
public static String train(Update input, String[] maxerror) {
	Thread thread = new Thread() {
		public void run() {
			MyAmazingBot MAB = new MyAmazingBot();
			try {
				DeepNetworkAbilities.Train(0, Double.parseDouble(maxerror[1]), basedir+"Training Data .csv", true);
				MAB.errorMessage(input, "Deep Learning Network has been trained!");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				MAB.errorMessage(input, e.toString());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				MAB.errorMessage(input, e.toString());
			}
		}
	};
	if(maxerror.length>1) {
	if(input.getMessage().getFrom().getId()==796720243) {
		thread.start();
		return "Starting training...";
	}else {
		return "--- You are not authorized to use this command ---";
	}
	}else {
		return "Enter a Maximum Error."
				+ "\nExample: /train 0.1";
	}
	
}
public static String boot(Update input, String[] tours){
	if(tours.length>1) {
	String tour = tours[1].toLowerCase();
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
			GameData.teamkeys.clear();
			GameData.teamkeys=(ArrayList<String>) HTTP.getTeamKeys(GameData.year+GameData.event);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MAB.errorMessage(input, e.toString());
		}
		try {
			GameData.matchdata.clear();
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
			CSVWriter cs = new CSVWriter();
			cs.WriteGameData(basedir+"Match Data.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			MAB.errorMessage(input, e.toString());
		}
		File file = new File(basedir+"DeepNetwork.zip");
		if(!file.exists()) {
			try {
				MAB.errorMessage(input, "Building Neural Network...");
				DeepNetworkAbilities.GenerateClassificationNet(100, 8, 100, 3);
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
		if(GameData.matchdata.get("frc"+teamnumber[1]).matches.size()>4) {
	try {
		System.out.println(teamnumber[1]);
		System.out.println(GameData.matchdata.get("frc"+teamnumber[1]).matches.size());
		INDArray data = DeepNetworkAbilities.calculate("frc"+teamnumber[1]);
	String out = "Team: "+ teamnumber[1] +"\n Matches Played: "+GameData.matchdata.get("frc"+teamnumber[1]).matches.size()
			+"\n Offensive Power Rating:\n"+GameData.matchdata.get("frc"+teamnumber[1]).OPR
			+"\n Defensive Power Rating:\n"+GameData.matchdata.get("frc"+teamnumber[1]).DPR
			+"\n Calculated Contribution to Winning Margin:\n"+GameData.matchdata.get("frc"+teamnumber[1]).CCWM
			+"\n Predicted Abilities:"
			+ "\n Rocket Hatch Low:  "+evaluate(data.getDouble(0))+" "+ round(data.getDouble(0),3)
			+ "\n Rocket Hatch Mid:  "+evaluate(data.getDouble(1))+" "+ round(data.getDouble(1),3)
			+ "\n Rocket Hatch High: "+evaluate(data.getDouble(2))+" "+ round(data.getDouble(2),3)
			+ "\n Rocket Cargo Low:  "+evaluate(data.getDouble(3))+" "+ round(data.getDouble(3),3)
			+ "\n Rocket Cargo Mid:  "+evaluate(data.getDouble(4))+" "+ round(data.getDouble(4),3)
			+ "\n Rocket Cargo High: "+evaluate(data.getDouble(5))+" "+ round(data.getDouble(5),3)
			+ "\n CargoShip Hatches: "+evaluate(data.getDouble(6))+" "+ round(data.getDouble(6),3)
			+ "\n CargoShip Cargo:   "+evaluate(data.getDouble(7))+" "+ round(data.getDouble(7),3);
	return out;
	}catch(Exception e) {
		return "There was an error while trying to retreive the data."
				+ " There is a possibility that the database has not been booted up yet."
				+ " The error in question is: "+e.toString();
	}
		}else {
			return "Not enough matches played"
					+ "\nMatches needed: 5"
					+ "\nMatches played: "+GameData.matchdata.get("frc"+teamnumber[1]).matches.size();
		}
		}catch(Exception e) {
			return "Team "+teamnumber[1]+" is not at this competition";
		}
	}else {
		return "Make sure to enter a team number."
				+ "\nExample: /stats 2486";
	}
}
public static String evaluate(double value) {
	if(value>=0.5) {
		return "YES";
	}else {
		return "NO";
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
