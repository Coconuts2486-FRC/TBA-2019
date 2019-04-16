package Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.common.reflect.TypeToken;

import Internet.HTTP;
import JSONing.JSON_Parsing;

public class GameData {
	public static String year = "2019";
	public static String event = "carv";
	public static HashMap<String, String> photoIDs = new HashMap<String, String>();
	public static ArrayList<String> teamkeys = new ArrayList<String>();
	public static HashMap<String,GameDataStructure> matchdata = new HashMap<String,GameDataStructure>();
	public static void uploadEventKey(String filePath) throws FileNotFoundException {
		File file = new File(filePath); 
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file); 
		String data = "";
		
		while(sc.hasNextLine()) {
			data+= sc.nextLine();
		}
        Type token = new TypeToken<String>(){
            private static final long serialVersionUID = 3909772501481775418L;
        }.getType();
        Gson gson = new Gson();
        GameData.event = gson.fromJson(data, token);
	}
	public static void uploadMatchData(String filePath) throws FileNotFoundException {
		File file = new File(filePath); 
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file); 
		String data = "";
		
		while(sc.hasNextLine()) {
			data+= sc.nextLine();
		}
        Type token = new TypeToken<HashMap<String, GameDataStructure>>(){
            private static final long serialVersionUID = 3909772501481775418L;
        }.getType();
        Gson gson = new Gson();
        GameData.matchdata = gson.fromJson(data, token);
	}
	public static void uploadPhotoIDs(String filePath) throws FileNotFoundException {
		File file = new File(filePath); 
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file); 
		String data = "";
		
		while(sc.hasNextLine()) {
			data+= sc.nextLine();
		}
        Type token = new TypeToken<HashMap<String, String>>(){
            private static final long serialVersionUID = 3909772501481775418L;
        }.getType();
        Gson gson = new Gson();
        GameData.photoIDs = gson.fromJson(data, token);
	}
	public static void uploadTeamKeys(String filePath) throws FileNotFoundException {
		File file = new File(filePath); 
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file); 
		String data = "";
		
		while(sc.hasNextLine()) {
			data+= sc.nextLine();
		}
        Type token = new TypeToken<ArrayList<String>>(){
            private static final long serialVersionUID = 3909772501481775418L;
        }.getType();
        Gson gson = new Gson();
        GameData.teamkeys = gson.fromJson(data, token);
	}
	public static void setMatchData() throws JsonSyntaxException, Exception {
		HashMap<String, OPRs> oprs = JSON_Parsing.OPRsToHashMap();
		JsonParser jsonParser = new JsonParser();
		JsonObject rankingArray = jsonParser.parse(HTTP.getRanking()).getAsJsonObject();
		for(int i = 0;i<GameData.teamkeys.size();i++) {
			GameDataStructure GD = new GameDataStructure();
			JsonObject teamData = jsonParser.parse(HTTP.getTeamName(GameData.teamkeys.get(i))).getAsJsonObject();
			GD.teamName= teamData.get("nickname").getAsString();
			JsonArray jArray = jsonParser.parse(HTTP.matches(GameData.teamkeys.get(i))).getAsJsonArray();
			try {
			GD.ranking= rankingArray.get(GameData.teamkeys.get(i)).getAsJsonObject().get("qual").getAsJsonObject().get("ranking").getAsJsonObject().get("rank").getAsInt();
			}catch(Exception e) {
				GD.ranking=0;
			}
			for(int ie = 0;ie<jArray.size();ie++) {
				match GDS = new match();
				try {
				JsonObject jObject = jArray.get(ie).getAsJsonObject();
				JsonObject baseblue = jObject.get("alliances").getAsJsonObject().get("blue").getAsJsonObject();
				JsonObject baseRed = jObject.get("alliances").getAsJsonObject().get("red").getAsJsonObject();
				String preListblue = baseblue.get("team_keys").getAsJsonArray().toString().replace("[", "").replace("]", "").replace("\"", "");
				String preListRed = baseRed.get("team_keys").getAsJsonArray().toString().replace("[", "").replace("]", "").replace("\"", "");
				ArrayList<String> listblue = new ArrayList<String>(Arrays.asList(preListblue.split(",")));
				ArrayList<String> listRed = new ArrayList<String>(Arrays.asList(preListRed.split(",")));
				if(listblue.contains(GameData.teamkeys.get(i))) {
					GDS.alliance="blue";
				}
				if(listRed.contains(GameData.teamkeys.get(i))) {
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
				if(oprs.containsKey(GameData.teamkeys.get(i))) {
				GD.OPR=oprs.get(GameData.teamkeys.get(i)).OPR;
				GD.DPR=oprs.get(GameData.teamkeys.get(i)).DPR;
				GD.CCWM=oprs.get(GameData.teamkeys.get(i)).CCWM;
				}else {
					GD.OPR=-100.0;
					GD.DPR=-100.0;
					GD.CCWM=-100.0;
				}
				GD.matches.add(GDS);
				}catch(Exception e) {
				}
			}
			GameData.matchdata.put(GameData.teamkeys.get(i), GD);
		}
		//Gson gson = new Gson();
		//gson.fromJson(jObject)
	}
}
