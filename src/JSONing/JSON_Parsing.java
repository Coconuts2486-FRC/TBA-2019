package JSONing;
import java.io.File;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.common.io.CharSink;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import Data.GameData;
import Data.OPRs;
import Internet.HTTP;

public class JSON_Parsing {
public static ArrayList<String> teamkeys(String input) {
	Type token = new TypeToken<ArrayList<String>>(){}.getType(); 
    Gson out = new Gson();
    return out.fromJson(input, token);
}
public static HashMap<String, String> photoIDs(String input) {
	Type token = new TypeToken<HashMap<String, String>>(){}.getType(); 
    Gson out = new Gson();
    return out.fromJson(input, token);
}

public static void WriteToFile(String data, String location) {
    File file = new File(location);
    CharSink sink = Files.asCharSink(file, StandardCharsets.UTF_8);
    try {
        sink.write(data);
    }
    catch(Exception ex)
    {
     System.out.println(ex);
    }
}
public static HashMap<String, OPRs> OPRsToHashMap() throws Exception {
	String data = HTTP.getTeamOPRs(GameData.year+GameData.event);
	JsonParser jsonParser = new JsonParser();
	JsonObject jObject = jsonParser.parse(data).getAsJsonObject();
	HashMap<String, OPRs> outro = new HashMap<String, OPRs>();
	for(int i=0;i<GameData.teamkeys.size();i++) {
		System.out.println(i);
		try {
		OPRs stat = new OPRs();
		stat.OPR=jObject.get("oprs").getAsJsonObject().get(GameData.teamkeys.get(i)).getAsDouble();
		stat.DPR=jObject.get("dprs").getAsJsonObject().get(GameData.teamkeys.get(i)).getAsDouble();
		stat.CCWM=jObject.get("ccwms").getAsJsonObject().get(GameData.teamkeys.get(i)).getAsDouble();
		outro.put(GameData.teamkeys.get(i), stat);
		}catch(Exception e) {
			System.out.println(GameData.teamkeys.get(i));
		}
	}
	return outro;
}
}
