package JSONing;


import java.util.HashMap;

import com.google.gson.Gson;

import Data.GameData;
import Internet.HTTP;

public class JSONGenerators {
	
public static String getAllMatchData() throws Exception {
	HashMap<String, String[]> teamMatches = new HashMap<String, String[]>();
	for(String s : GameData.teamkeys) {
		//teamMatches.put(s, HTTP.matches(s));
	}
	
	Gson out = new Gson();
	for(int i = 0; i<GameData.teamkeys.size();i++) {
		out.toJson(HTTP.matches(GameData.teamkeys.get(i)));
	}
	return out.toString();
}
}
