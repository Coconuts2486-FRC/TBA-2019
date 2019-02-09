import java.util.ArrayList;
import java.util.HashMap;

public class Run {
	//2019azfl Flagstaff
	//2019cave Ventura
	//team/frc2486/matches/2019
	public static ArrayList<String> teamkeys = new ArrayList<String>();
	public static HashMap<String,String> teammatchdata = new HashMap<String,String>();
	public static HashMap<String, ArrayList<Integer>> scores = new HashMap<String,ArrayList<Integer>>();
	
	public static String year = "2018";
	public static String event = "azfl";
	public static void main(String[] args) throws Exception {
		teamkeys=JSON_Parsing.teamkeys(HTTP.getTeamKeys(year+event));
		System.out.println(teamkeys);
		for(int i = 0; i<teamkeys.size();i++) {
			double round = i;
			double length = teamkeys.size();
			teammatchdata.put(teamkeys.get(i), HTTP.matches(teamkeys.get(i)));
			int per = (int) ((round/length)*100);
			System.out.println("% "+per);
		}
		for(int i = 0;i<teamkeys.size();i++) {
			scores.put(teamkeys.get(i), JSON_Parsing.teamMatchScore(teamkeys.get(i), teammatchdata.get(teamkeys.get(i))));
		}
		System.out.println(scores.toString());
		JSON_Parsing.WriteToFile(teammatchdata.toString(), "/Users/logan42474/Desktop/AllMatchData.txt");
		JSON_Parsing.WriteToFile(scores.toString(), "/Users/logan42474/Desktop/MatchScores.txt");
		
	}

}
