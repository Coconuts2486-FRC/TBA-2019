package Telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

import Data.GameData;
import JSONing.JSONGenerators;
import JSONing.JSON_Parsing;

public class PhotoCommands {
	static String basedir = System.getProperty("user.home")+"/Desktop/FRC 2019 Data/";
	public static void process(Update data) {
		MyAmazingBot MAB = new MyAmazingBot();
		String photoid = data.getMessage().getPhoto().get(0).getFileId();
		if(data.getMessage().getFrom().getId()==796720243) {
			if(!(data.getMessage().getCaption()==null)) {
				String caption = data.getMessage().getCaption();
				if(GameData.matchdata.containsKey("frc"+caption)) {
					if(!GameData.photoIDs.containsKey("frc"+caption)) {
						GameData.photoIDs.put("frc"+caption, photoid);
						try {
							JSON_Parsing.WriteToFile(JSONGenerators.getAllPhotoIDs(), basedir+"PhotoIDs.txt");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						MAB.errorMessage(data, "Team "+caption+" now has a photo");
					}else {
						MAB.errorMessage(data, "Team "+caption+" already has a photo");
					}
				}else {
					MAB.errorMessage(data, "Team "+caption+" is not at this competition");
				}
			}else {
				MAB.errorMessage(data, "Image does not have a caption. Example: 2486");
			}
		}else {
			MAB.errorMessage(data, "--- You are not authorized to use this command ---");
		}
	}
}
