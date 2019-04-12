import java.io.File;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import Data.GameData;
import Data.Secretkeys;
import Telegram.MyAmazingBot;

public class Run {
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
		File ids = new File( basedir+"PhotoIDs.txt");
		if(ids.exists()) {
			GameData.uploadPhotoIDs( basedir+"PhotoIDs.txt");
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