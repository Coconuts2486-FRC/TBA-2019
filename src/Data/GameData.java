package Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import Internet.HTTP;

public class GameData {
	public static String year = "2019";
	public static String event = "week0";
	// teamkeys of all teams at an event
	public static ArrayList<String> teamkeys = new ArrayList<String>();
	// Specific alliance data that a team was on
	public static HashMap<String,ArrayList<GameDataStructure>> matchdata = new HashMap<String,ArrayList<GameDataStructure>>();
	
	public static void setMatchData() throws JsonSyntaxException, Exception {
		for(int i = 0;i<GameData.teamkeys.size();i++) {
			ArrayList<GameDataStructure> output = new ArrayList<GameDataStructure>();
			JsonParser jsonParser = new JsonParser();
			JsonArray jArray = jsonParser.parse(HTTP.matches(GameData.teamkeys.get(i))).getAsJsonArray();
			for(int ie = 0;ie<jArray.size();ie++) {
				GameDataStructure GDS = new GameDataStructure();
				JsonObject jObject = jArray.get(ie).getAsJsonObject();
				JsonObject baseBlue = jObject.get("alliances").getAsJsonObject().get("blue").getAsJsonObject();
				JsonObject baseRed = jObject.get("alliances").getAsJsonObject().get("red").getAsJsonObject();
				String preListBlue = baseBlue.get("team_keys").getAsJsonArray().toString().replace("[", "").replace("]", "").replace("\"", "");
				String preListRed = baseRed.get("team_keys").getAsJsonArray().toString().replace("[", "").replace("]", "").replace("\"", "");
				ArrayList<String> listBlue = new ArrayList<String>(Arrays.asList(preListBlue.split(",")));
				ArrayList<String> listRed = new ArrayList<String>(Arrays.asList(preListRed.split(",")));
				if(listBlue.contains(GameData.teamkeys.get(i))) {
					GDS.alliance="blue";
				}
				if(listRed.contains(GameData.teamkeys.get(i))) {
					GDS.alliance="red";
				}
				//Setting Specific Data
				
				//blue
				GDS.blueData.score=baseBlue.get("score").getAsDouble();
				GDS.blueData.teamkey1=listBlue.get(0);
				GDS.blueData.teamkey2=listBlue.get(1);
				GDS.blueData.teamkey3=listBlue.get(2);
				//red
				output.add(GDS);
			}
			GameData.matchdata.put(GameData.teamkeys.get(i), output);
		}
		//Gson gson = new Gson();
		//gson.fromJson(jObject)
	}
}
