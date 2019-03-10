package Telegram;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import Data.Secretkeys;

public class MyAmazingBot extends TelegramLongPollingBot {
    public void onUpdateReceived(Update update) {
    
    	 if (update.hasMessage() && update.getMessage().hasText()) {
    		 if(update.getMessage().getChatId().toString().equals("796720243")||update.getMessage().getText().contains("314159")) {
    			 SendMessage message = new SendMessage().setChatId(update.getMessage().getChatId())
    					 .setText(Commands.main(update));
    			 try {
    		            execute(message); // Call method to send the message
    		        } catch (TelegramApiException e) {
    		            e.printStackTrace();
    		        }
    		 }else {
    			 SendMessage message = new SendMessage().setChatId(update.getMessage().getChatId())
    					 .setText("You are not authorized to use this bot.");
    			 try {
    		            execute(message); // Call method to send the message
    		        } catch (TelegramApiException e) {
    		            e.printStackTrace();
    		        }
    		 }
    	    }
    }
    public void errorMessage(Update data, String text) {
    	 SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
	                .setChatId(data.getMessage().getChatId())
	                .setText(text);
	        try {
	            execute(message); // Call method to send the message
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