import org.json.JSONArray;
import org.json.JSONObject;

public class JSON_Parsing {
public static void AdverageScore(String input) {
	double all = 0;
	//input = "\"data\": "+input;
	//System.out.println("Two: ----------"+input);
	JSONArray arr = new JSONArray(input);
	//JSONObject obj = new JSONObject(input);
	
	int output = arr.getJSONObject(0).getJSONObject("alliances").getJSONObject("red").getInt("score");
	for(int i = 0; i< arr.length();i++) {
		boolean notfound = true;
		for(int ie = 0;ie<3;ie++) {
			if(arr.getJSONObject(i).getJSONObject("alliances").getJSONObject("red").getJSONArray("team_keys").getString(ie).equals("frc2486")) {
				System.out.println("Red");
				all+=arr.getJSONObject(i).getJSONObject("alliances").getJSONObject("red").getDouble("score");
			}
			if(arr.getJSONObject(i).getJSONObject("alliances").getJSONObject("blue").getJSONArray("team_keys").getString(ie).equals("frc2486")) {
				System.out.println("Blue");
				all+=arr.getJSONObject(i).getJSONObject("alliances").getJSONObject("blue").getDouble("score");
			}
		}
		//if(arr.getJSONObject(i).getJSONObject("alliances").getJSONObject("red").getJSONArray("team_keys").)
	}
	System.out.println(all);
	System.out.println(all/arr.length());
	//System.out.println(arr.length());
	//System.out.println(output);
}
}
