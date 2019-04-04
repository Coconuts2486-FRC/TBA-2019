package JSONing;

import com.google.gson.GsonBuilder;
import Data.GameData;
import Data.Secretkeys;

public class JSONGenerators {
	
public static String getAllMatchData() throws Exception {
	String gson = new GsonBuilder().setPrettyPrinting().create().toJson(GameData.matchdata);
	return gson;
}
public static String getAllPhotoIDs() throws Exception {
	String gson = new GsonBuilder().setPrettyPrinting().create().toJson(GameData.photoIDs);
	return gson;
}
public static String getAllTeamKeys() throws Exception {
	String gson = new GsonBuilder().setPrettyPrinting().create().toJson(GameData.teamkeys);
	return gson;
}
public static String getAllKeys() {
	String gson = new GsonBuilder().setPrettyPrinting().create().toJson(Secretkeys.keys);
	return gson;
}
public static String getEventKey() {
	String gson = new GsonBuilder().setPrettyPrinting().create().toJson(GameData.event);
	return gson;
}
}
