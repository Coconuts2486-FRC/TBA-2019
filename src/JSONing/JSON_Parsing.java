package JSONing;
import java.io.File;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import com.google.common.io.CharSink;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JSON_Parsing {
public static ArrayList<String> teamkeys(String input) {
	Type token = new TypeToken<ArrayList<String>>(){}.getType(); 
    Gson out = new Gson();
    return out.fromJson(input.toString(), token);
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
}
