package Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import JSONing.JSONGenerators;
import JSONing.JSON_Parsing;

public class Secretkeys {
	
	@Expose(serialize = false, deserialize = false)
	static String basedir = System.getProperty("user.home")+"/Desktop/FRC 2019 Data/";
	public static Secretkeys.Keys keys = new Secretkeys().new Keys();
	
	public static void startup() throws FileNotFoundException {
		File keys = new File(basedir+"keys.txt");
		if(keys.exists()) {
			System.out.println("Keys Exist!");
			loadkeys();
		} else {
			System.out.println("Keys Do Not Exist");
		}
	}
	
	public static void loadkeys() throws FileNotFoundException {
		File file = new File(basedir+"keys.txt"); 
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(file); 
		String data = "";
		
		while(sc.hasNextLine()) {
			data+= sc.nextLine();
		}
		Gson gson = new Gson();
		Secretkeys.keys = gson.fromJson(data, Secretkeys.Keys.class);
	}
	
	public static void savekeys() {
	JSON_Parsing.WriteToFile(JSONGenerators.getAllKeys(),basedir+"keys.txt");
	}

	public class Keys {
		public String TBAKey;
		public String Telegramkey;
	}
}
