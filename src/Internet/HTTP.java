package Internet;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Data.GameData;
import Data.Secretkeys;
import jdk.nashorn.internal.parser.JSONParser;

public class HTTP {

	public static String APIRead(String extension) throws Exception {

        StringBuilder result = new StringBuilder();

        URL theBA = new URL("https://www.thebluealliance.com/api/v3/" + extension);
        HttpURLConnection con = (HttpURLConnection) theBA.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla");
        con.setRequestMethod("GET");
        con.addRequestProperty("X-TBA-Auth-Key", Secretkeys.keys.TBAKey);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String response;
        while ((response = in.readLine()) != null) {
            result.append(response);
        }
        /*
        Type token = new TypeToken<ArrayList<String>>(){}.getType();
        
        Gson out = new Gson();
        out.fromJson(result.toString(), token);
        */
        in.close();
        return result.toString();
    }

	public static ArrayList<String> getTeamKeys(String eventkey) throws Exception {
		Type token = new TypeToken<ArrayList<String>>(){}.getType(); 
	    Gson out = new Gson();
	    return out.fromJson(APIRead("event/"+eventkey+"/teams/keys"), token);
		
	}
	
	public static String matches(String teamkey) throws Exception {
		return APIRead("team/"+teamkey+"/matches/"+GameData.year);
		
	}
	public static String events() throws Exception {
		return APIRead("events/"+GameData.year);
		
	}
	
	
}
