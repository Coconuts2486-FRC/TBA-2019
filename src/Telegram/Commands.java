package Telegram;

import org.telegram.telegrambots.meta.api.objects.Update;

public class Commands {
public static String main(Update input) {
	String[] formatted = format(input);
		String output = null;
		switch(formatted[0]) {
		case "dks": output="Hash something";
		break;
		case "sd": output="Get something random";
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
}
