package Telegram;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.google.gson.JsonSyntaxException;
import Artificial_Intelligence.DeepNetworkAbilities;
import Artificial_Intelligence.DeepNetworkAverage;
import CSV.CSVWriter;
import Data.Formulas;
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
		case "/statsadv": output=statsadv(input, formatted);
		break;
		case "/stats": output=stats(input, formatted);
		break;
		case "/boot": output=boot(input,formatted);
		break;
		case "/update": output=update(input);
		break;
		case "/train": output=train(input,formatted);
		break;
		case "/stoptraining": output=stoplearning(input);
		break;
		case "/start": output="To get statistics on teams use the command \n/stats"
				+ "\nExample: /stats 2486";
		break;
		default:  output="Error 404: Command Not Found";
		}
		return output;
}
public static String statsadv(Update update, String[] teamnumber) {
	MyAmazingBot MAB = new MyAmazingBot();
	if(teamnumber.length>1) {
		try {
		if(GameData.matchdata.get("frc"+teamnumber[1]).matches.size()>4) {
	try {
		INDArray data = DeepNetworkAbilities.calculate("frc"+teamnumber[1]);
		INDArray datab = DeepNetworkAverage.calculate("frc"+teamnumber[1]);
		if(GameData.photoIDs.containsKey("frc"+teamnumber[1])) {
		MAB.sendPhoto(update, GameData.photoIDs.get("frc"+teamnumber[1]));
		}
		MAB.errorMessage(update, "Team: "+ teamnumber[1] +"\nName: "+GameData.matchdata.get("frc"+teamnumber[1]).teamName+"\nRank: "+GameData.matchdata.get("frc"+teamnumber[1]).ranking+"\nMatches Played: "+GameData.matchdata.get("frc"+teamnumber[1]).matches.size());
		String out = 
			"\n Offensive Power Rating:\n    "+GameData.matchdata.get("frc"+teamnumber[1]).OPR
			+"\n Defensive Power Rating:\n    "+GameData.matchdata.get("frc"+teamnumber[1]).DPR
			+"\n Calculated Contribution to Winning Margin:\n    "+GameData.matchdata.get("frc"+teamnumber[1]).CCWM
			+"\n Predicted Abilities:"
			+ "\n    Rocket Hatch Low:  "+evaluate(data.getDouble(0))+" "+ round(data.getDouble(0),3)
			+ "\n    Rocket Hatch Mid:  "+evaluate(data.getDouble(1))+" "+ round(data.getDouble(1),3)
			+ "\n    Rocket Hatch High: "+evaluate(data.getDouble(2))+" "+ round(data.getDouble(2),3)
			+ "\n    Rocket Cargo Low:  "+evaluate(data.getDouble(3))+" "+ round(data.getDouble(3),3)
			+ "\n    Rocket Cargo Mid:  "+evaluate(data.getDouble(4))+" "+ round(data.getDouble(4),3)
			+ "\n    Rocket Cargo High: "+evaluate(data.getDouble(5))+" "+ round(data.getDouble(5),3)
			+ "\n    CargoShip Hatches: "+evaluate(data.getDouble(6))+" "+ round(data.getDouble(6),3)
			+ "\n    CargoShip Cargo:   "+evaluate(data.getDouble(7))+" "+ round(data.getDouble(7),3);
			MAB.errorMessage(update, out);
			String outb = 
			" Predicted Averages:"
			+ "\n    Rocket Hatch Low:  "+ round(datab.getDouble(0),4)
			+ "\n    Rocket Hatch Mid:  "+ round(datab.getDouble(1),4)
			+ "\n    Rocket Hatch High: "+ round(datab.getDouble(2),4)
			+ "\n    Rocket Cargo Low:  "+ round(datab.getDouble(3),4)
			+ "\n    Rocket Cargo Mid:  "+ round(datab.getDouble(4),4)
			+ "\n    Rocket Cargo High: "+ round(datab.getDouble(5),4)
			+ "\n    CargoShip Hatches: "+ round(datab.getDouble(6),4)
			+ "\n    CargoShip Cargo:   "+ round(datab.getDouble(7),4);
			MAB.errorMessage(update, outb);
			MAB.errorMessage(update, Formulas.averageScore("frc"+teamnumber[1]));
	return Formulas.climbInfo("frc"+teamnumber[1]);
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
public static String update(Update input) {
	Thread thread = new Thread() {
		public void run() {
			MyAmazingBot MAB = new MyAmazingBot();
			switch(GameData.event) {
			case "azfl": MAB.errorMessage(input, "Updating Arizona North");
			break;
			case "carv": MAB.errorMessage(input, "Updating the Carver Division");
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
public static String stoplearning(Update input) {
	if(input.getMessage().getFrom().getId()==796720243) {
	if(DeepNetworkAbilities.training||DeepNetworkAverage.training) {
		DeepNetworkAbilities.training=false;
		DeepNetworkAverage.training=false;
	return "Training has stopped";
	}else {
	return "... It was never being trained...";
	}
	}else {
		return "--- You are not authorized to use this command ---";
	}
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
public static String train(Update input, String[] maxerror) {
	Thread thread = new Thread() {
		public void run() {
			MyAmazingBot MAB = new MyAmazingBot();
			try {
				DeepNetworkAbilities.training=true;
				DeepNetworkAbilities.Train(0, Double.parseDouble(maxerror[2]), basedir+"Training Data .csv", true);
				DeepNetworkAbilities.training=false;
				MAB.errorMessage(input, "Deep Learning Network has been trained!");
				MAB.errorMessage(input, "Saving Network...");
				try {
					DeepNetworkAbilities.saveModel(basedir+"DeepNetwork.zip");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MAB.errorMessage(input, "Done!");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				MAB.errorMessage(input, e.toString());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				MAB.errorMessage(input, e.toString());
			}
		}
	};
	Thread threadb = new Thread() {
		public void run() {
			MyAmazingBot MAB = new MyAmazingBot();
			try {
				DeepNetworkAverage.training=true;
				DeepNetworkAverage.Train(0, Double.parseDouble(maxerror[2]), basedir+"Training Data b.csv", true);
				DeepNetworkAverage.training=false;
				MAB.errorMessage(input, "Deep Learning Network has been trained!");
				MAB.errorMessage(input, "Saving Network...");
				try {
					DeepNetworkAbilities.saveModel(basedir+"DeepNetworkb.zip");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MAB.errorMessage(input, "Done!");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				MAB.errorMessage(input, e.toString());
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				MAB.errorMessage(input, e.toString());
			}
		}
	};
	if(maxerror.length>2) {
	if(input.getMessage().getFrom().getId()==796720243) {
		if(maxerror[1].toLowerCase().equals("a")) {
			thread.start();
			return "Starting training...";
		}else if(maxerror[1].toLowerCase().equals("b")) {
			threadb.start();
			return "Starting training...";
		}else {
			return "There are two Neural Networks"
					+ "\nA: The Abilities Network"
					+ "\nB: The Average Network";
		}
	}else {
		return "--- You are not authorized to use this command ---";
	}
	}else {
		return "Choose the network and Maximum error."
				+ "\nExample: /train a 0.1";
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
		if(tour.equals("carver")||tour.equals("carv")||tour.equals("world")||tour.equals("worlds")||tour.equals("champs")||tour.equals("championship")||tour.equals("championships")) {
			GameData.event="carv";
			MAB.errorMessage(input, "Tournament set to the Carver Division");
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
			System.out.println("TEAMKEYS: "+GameData.teamkeys);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MAB.errorMessage(input, e.toString());
			System.out.println("I NEED HELP!!!");
		}
		try {
			System.out.println("AAA");
			GameData.matchdata.clear();
			System.out.println("BBB");
			GameData.setMatchData();
			System.out.println("CCC");
		} catch (JsonSyntaxException e) {
			System.out.println("I NEED HELP!!!22222");
			MAB.errorMessage(input, e.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			MAB.errorMessage(input, e.toString());
		}
		MAB.errorMessage(input, "Saving Data...");
		try {
			System.out.println("DDD");
			JSON_Parsing.WriteToFile(JSONGenerators.getAllTeamKeys(), basedir+"Team Keys.txt");
			JSON_Parsing.WriteToFile(JSONGenerators.getAllMatchData(), basedir+"Match Data.txt");
			JSON_Parsing.WriteToFile(JSONGenerators.getEventKey(), basedir+"Event Key.txt");
			System.out.println("EEE");
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
		File fileb = new File(basedir+"DeepNetworkb.zip");
		if(!fileb.exists()) {
			try {
				MAB.errorMessage(input, "Building Neural Network...");
				DeepNetworkAverage.GenerateAverageNet(100, 8, 100, 3);
				DeepNetworkAbilities.saveModel(basedir+"DeepNetworkb.zip");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}else {
			MAB.errorMessage(input, "Loading Neural Network...");
			try {
				DeepNetworkAverage.loadModel(basedir+"DeepNetworkb.zip");
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
public static String stats(Update update, String[] teamnumber) {
	MyAmazingBot MAB = new MyAmazingBot();
	if(teamnumber.length>1) {
		try {
		if(GameData.matchdata.get("frc"+teamnumber[1]).matches.size()>4) {
	try {
		INDArray data = DeepNetworkAbilities.calculate("frc"+teamnumber[1]);
		if(GameData.photoIDs.containsKey("frc"+teamnumber[1])) {
		MAB.sendPhoto(update, GameData.photoIDs.get("frc"+teamnumber[1]));
		}
		MAB.errorMessage(update, "Team: "+ teamnumber[1] +"\nName: "+GameData.matchdata.get("frc"+teamnumber[1]).teamName+"\nRank: "+GameData.matchdata.get("frc"+teamnumber[1]).ranking+"\nMatches Played: "+GameData.matchdata.get("frc"+teamnumber[1]).matches.size());
		String out = 
			"\n Offensive Power Rating:\n    "+GameData.matchdata.get("frc"+teamnumber[1]).OPR
			+"\n Defensive Power Rating:\n    "+GameData.matchdata.get("frc"+teamnumber[1]).DPR
			+"\n Calculated Contribution to Winning Margin:\n    "+GameData.matchdata.get("frc"+teamnumber[1]).CCWM
			+"\n Predicted Abilities:"
			+ "\n    Rocket Hatch Low:  "+evaluate(data.getDouble(0))+" "+ round(data.getDouble(0),3)
			+ "\n    Rocket Hatch Mid:  "+evaluate(data.getDouble(1))+" "+ round(data.getDouble(1),3)
			+ "\n    Rocket Hatch High: "+evaluate(data.getDouble(2))+" "+ round(data.getDouble(2),3)
			+ "\n    Rocket Cargo Low:  "+evaluate(data.getDouble(3))+" "+ round(data.getDouble(3),3)
			+ "\n    Rocket Cargo Mid:  "+evaluate(data.getDouble(4))+" "+ round(data.getDouble(4),3)
			+ "\n    Rocket Cargo High: "+evaluate(data.getDouble(5))+" "+ round(data.getDouble(5),3)
			+ "\n    CargoShip Hatches: "+evaluate(data.getDouble(6))+" "+ round(data.getDouble(6),3)
			+ "\n    CargoShip Cargo:   "+evaluate(data.getDouble(7))+" "+ round(data.getDouble(7),3);
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
