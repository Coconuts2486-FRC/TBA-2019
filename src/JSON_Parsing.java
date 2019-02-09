import org.json.JSONObject;

public class JSON_Parsing {
public static void test(String input) {
	JSONObject obj = new JSONObject(input);
	int currentyear = obj.getInt("current_season");
	System.out.println(currentyear);
}
}
