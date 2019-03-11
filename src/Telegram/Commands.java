package Telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

import Artificial_Intelligence.DeepNetworkAbilities;
import Data.GameData;

public class Commands {
public static String main(Update input) {
	System.out.println(input.getMessage().getFrom().getUserName()+": "+input.getMessage().getText());
	String[] formatted = format(input);
		String output = null;
		switch(formatted[0].toLowerCase()) {
		case "/stats": output=stats(formatted[1]);
		break;
		case "/boot": output="Booting up...";
		break;
		case "/start": output="To get statistics on teams type '/stats {team number}'";
		break;
		default:  output="Error 404: Command Not Found";
		}
		return output;
}
private static String[] format(Update input) {
	if(input.getMessage().getText().contains(" ")) {
		String[] output = input.getMessage().getText().split(" ");
		return output;
	}else {
		String[] output = {input.getMessage().getText()};
		return output;
	}
}
public static String stats(String teamnumber) {
	try {
	String out = "Team: "+ teamnumber +"\n Matches Played: "+GameData.matchdata.get("frc"+teamnumber).size()
			+"\n Predicted Abilities:"
			+ "\n Rocket Hatch Low:  "+ DeepNetworkAbilities.calculate("frc"+teamnumber).getDouble(0)
			+ "\n Rocket Hatch Mid:  "+ DeepNetworkAbilities.calculate("frc"+teamnumber).getDouble(1)
			+ "\n Rocket Hatch High: "+ DeepNetworkAbilities.calculate("frc"+teamnumber).getDouble(2)
			+ "\n Rocket Cargo Low:  "+ DeepNetworkAbilities.calculate("frc"+teamnumber).getDouble(3)
			+ "\n Rocket Cargo Mid:  "+ DeepNetworkAbilities.calculate("frc"+teamnumber).getDouble(4)
			+ "\n Rocket Cargo High: "+ DeepNetworkAbilities.calculate("frc"+teamnumber).getDouble(5)
			+ "\n CargoShip Hatches: "+ DeepNetworkAbilities.calculate("frc"+teamnumber).getDouble(6)
			+ "\n CargoShip Cargo:   "+ DeepNetworkAbilities.calculate("frc"+teamnumber).getDouble(7);
	return out;
	}catch(Exception e) {
		return "There was an error while trying to retreive the data. There is a possibility that the database has not been booted up yet."
				+ " The error in question is: "+e.toString();
	}
}
}
