package Data;

import java.util.ArrayList;
import java.util.HashMap;

public class GameData {
	public static String year = "2018";
	public static String event = "azfl";
	// teamkeys of all teams at an event
	public static ArrayList<String> teamkeys = new ArrayList<String>();
	// JSON string of teams matches
	public static HashMap<String,String> teammatchdata = new HashMap<String,String>();
	// Every teams match scores
	public static HashMap<String, ArrayList<Integer>> scores = new HashMap<String,ArrayList<Integer>>();
	// Specific alliance data that a team was on
	public static HashMap<String,GameDataStructure> teammatchalliancedata = new HashMap<String,GameDataStructure>();
}
