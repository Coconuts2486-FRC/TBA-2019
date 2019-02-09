import java.util.ArrayList;

public class Run {
	//2019azfl Flagstaff
	//2019cave Ventura
	//team/frc2486/matches/2019
	public static ArrayList<String> teamkeys = new ArrayList<String>();
	public static ArrayList<String> teammatchdata = new ArrayList<String>();
	public static String year = "2018";
	public static String event = "azfl";
	public static void main(String[] args) throws Exception {
		teamkeys=JSON_Parsing.teamkeys(HTTP.getTeamKeys(year+event));
		System.out.println(teamkeys);
		for(int i = 0; i<teamkeys.size();i++) {
			teammatchdata.add(HTTP.matches(teamkeys.get(i)));
		}
		JSON_Parsing.WriteToFile(teammatchdata.toString(), "/Users/logan42474/Desktop/DataStuff");
		
	}

}
