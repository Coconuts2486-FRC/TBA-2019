import java.io.File;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import Data.Secretkeys;
import Telegram.MyAmazingBot;

public class Run {
	//2019azfl Flagstaff
	//2019cave Ventura
	//team/frc2486/matches/2019
	static String basedir = System.getProperty("user.home")+"/Desktop/FRC 2019 Data/";
	public static void main(String[] args) throws Exception {
		System.out.println("Starting Up!");
		System.out.println(System.getProperty("user.home"));
		File baseFile = new File(basedir);
		if(baseFile.exists()) {
			System.out.println("Base Directory Exists!");
		}else {
			System.out.println("Creating Base Directory");
			baseFile.mkdir();
		}
		
		Secretkeys.startup();
		
		System.out.println("Starting Telegram Server!");
		ApiContextInitializer.init();
    	TelegramBotsApi botsApi = new TelegramBotsApi();

         try {
             botsApi.registerBot(new MyAmazingBot()); 
             System.out.println("Server is Running!");
         } catch (TelegramApiException e1) {
             e1.printStackTrace();
         }
		
	}

}