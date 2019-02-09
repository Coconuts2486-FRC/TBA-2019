import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTP {
	public static String APIRead(String extension) throws Exception {

        StringBuilder result = new StringBuilder();

        URL theBA = new URL("https://www.thebluealliance.com/api/v3/" + extension);
        HttpURLConnection con = (HttpURLConnection) theBA.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla");
        con.setRequestMethod("GET");
        con.addRequestProperty("X-TBA-Auth-Key", "rwG5JxLtpFOJKr9jqDclVyGkUHxGYaDC0OoL9a8IZj4cWh5lWtZxJ1OnWn8xva5Y");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String response;
        while ((response = in.readLine()) != null) {
            result.append(response);
        }
        
        in.close();
        return result.toString();
    }
	public static String getTeamKeys(String eventkey) throws Exception {
		return APIRead("event/"+eventkey+"/teams/keys");
		
	}
	public static String matches(String teamkey) throws Exception {
		return APIRead("team/"+teamkey+"/matches/"+Run.year);
		
	}
	
}
