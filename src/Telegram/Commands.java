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
			+ "\n Rocket Hatch Low:  "+ round(DeepNetworkAbilities.calculate("frc"+teamnumber).getDouble(0),5)
			+ "\n Rocket Hatch Mid:  "+ round(DeepNetworkAbilities.calculate("frc"+teamnumber).getDouble(1),5)
			+ "\n Rocket Hatch High: "+ round(DeepNetworkAbilities.calculate("frc"+teamnumber).getDouble(2),5)
			+ "\n Rocket Cargo Low:  "+ round(DeepNetworkAbilities.calculate("frc"+teamnumber).getDouble(3),5)
			+ "\n Rocket Cargo Mid:  "+ round(DeepNetworkAbilities.calculate("frc"+teamnumber).getDouble(4),5)
			+ "\n Rocket Cargo High: "+ round(DeepNetworkAbilities.calculate("frc"+teamnumber).getDouble(5),5)
			+ "\n CargoShip Hatches: "+ round(DeepNetworkAbilities.calculate("frc"+teamnumber).getDouble(6),5)
			+ "\n CargoShip Cargo:   "+ round(DeepNetworkAbilities.calculate("frc"+teamnumber).getDouble(7),5);
	return out;
	}catch(Exception e) {
		return "There was an error while trying to retreive the data. There is a possibility that the database has not been booted up yet."
				+ " The error in question is: "+e.toString();
	}
}
public static double round(double value, int places) {
    if (places < 0) throw new IllegalArgumentException();

    long factor = (long) Math.pow(10, places);
    value = value * factor;
    long tmp = Math.round(value);
    return (double) tmp / factor;
}
}
