package JSONing;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import Data.GameData;
import Internet.HTTP;

public class JSONGenerators {
	
public static String getAllMatchData() throws Exception {
	HashMap<String,String> out = new HashMap<String,String>();
	for(int i = 0; i<GameData.teamkeys.size();i++) {
		//JsonParser jsonParser = new JsonParser();
	    //JsonArray jo = (JsonArray)jsonParser.parse(HTTP.matches(GameData.teamkeys.get(0)));
	    //JsonObject jsonOb = (JsonObject) jo.get(0);
	    
	    //Gson googleJson = new Gson();
	    //ArrayList<?> jsonObject = googleJson.fromJson(jo, ArrayList.class);
	    out.put(GameData.teamkeys.get(i), HTTP.matches(GameData.teamkeys.get(i)));
	}
	//System.out.println(out);
	String gson = new GsonBuilder().setPrettyPrinting().create().toJson(out);
	//gson=gson.replace("\\", "");
	return gson;
}
}
