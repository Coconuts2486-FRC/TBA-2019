package Telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import Data.Secretkeys;

public class MyAmazingBot extends TelegramLongPollingBot {
    public void onUpdateReceived(Update update) {
    
    	 if (update.hasMessage() && update.getMessage().hasText()) {
    			 SendMessage message = new SendMessage().setChatId(update.getMessage().getChatId())
    					 .setText(Commands.main(update));
    			 try {
    		            execute(message);
    		        } catch (TelegramApiException e) {
    		            e.printStackTrace();
    		        }
    		 }
    	 if(update.getMessage().hasPhoto()) {
    		 PhotoCommands.process(update);
    	    }
    }
    public void errorMessage(Update data, String text) {
    	 	SendMessage message = new SendMessage().setChatId(data.getMessage().getChatId()).setText(text);
	        try {
	           execute(message);
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
    }
    public void sendPhoto(Update data, String photoid) {
    	SendPhoto photo = new SendPhoto().setChatId(data.getMessage().getChatId()).setPhoto(photoid);
		 try {
	            execute(photo);
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
   }

    public String getBotUsername() {
        // TODO
        return "Coconuts_2486_bot";
    }

    @Override
    public String getBotToken() {
        // TODO
        return Secretkeys.keys.Telegramkey;
    }
}