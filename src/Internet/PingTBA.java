package Internet;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class PingTBA {
	public long ping() {
		HttpURLConnection connection = null;
	    try {
	    		long StartTime = System.nanoTime();
	    		System.out.println("Start: "+StartTime);
	        URL u = new URL("https://www.thebluealliance.com/");
	        connection = (HttpURLConnection) u.openConnection();
	        connection.setRequestMethod("HEAD");
	        long EndTime = System.nanoTime();
	        System.out.println("End: "+EndTime);
	        System.out.println("Total: "+(EndTime-StartTime)/1000000);
	        return (long) ((System.nanoTime() - StartTime)/1000000);
	    } catch (MalformedURLException e) {
	        return 500;
	    } catch (IOException e) {
	        return 500;
	    } finally {
	        if (connection != null) {
	            connection.disconnect();
	        }
	    }
	}
}
