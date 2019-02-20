package JSONing;

import com.google.gson.GsonBuilder;
import Data.GameData;

public class JSONGenerators {
	
public static String getAllMatchData() throws Exception {
	String gson = new GsonBuilder().setPrettyPrinting().create().toJson(GameData.matchdata);
	return gson;
}
}
