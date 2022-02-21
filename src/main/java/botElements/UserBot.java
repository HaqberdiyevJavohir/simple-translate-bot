package  botElements;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import  model.TranslateModel.Def;
import  model.TranslateModel.Tr;
import  model.TranslateModel.Translate;
import  model.User;
import  model.UserState;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class UserBot extends TelegramLongPollingBot implements BotUtils{
    @Override
    public void onUpdateReceived(Update update) {
        BotService botService = new BotService();
        if (update.hasMessage()){
            SendMessage sendMessage = new SendMessage();
            String text = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            User user = botService.read(chatId);
            UserState userState = null;
            if (user == null){
                userState = UserState.START;
            }else {
                userState = user.getUserState();
            }
            if (text.equals("/start")){
                String fullname = update.getMessage().getFrom().getFirstName() + " " + update.getMessage().getFrom().getLastName();
                User newUser = new User(fullname,userState,chatId);
                botService.add(newUser);
                sendMessage.setText("Assalom alaykum " + fullname +
                        "\nTarjimon dasturiga xush kelibsiz!!!\nDasturni ishga tushirish uchun quyidagilardan birini tanlang:");
                sendMessage.setChatId(String.valueOf(chatId));
                sendMessage.setReplyMarkup(botService.markup());
            }else if(user.getUserState().equals("START")) {
                sendMessage.setText("Siz menyudan tilni tanlamagansiz!!!\nDasturni ishga tushirish uchun quyidagilardan birini tanlang:");
                sendMessage.setChatId(String.valueOf(chatId));
                sendMessage.setReplyMarkup(botService.markup());
            }else if (text.equals("English - Russian")){
                userState = UserState.ENGRU;
                botService.setUserSate(user,userState);
                sendMessage.setText("Siz ingliz tilidan rus tiliga tarjima qilishingiz mumkin. Biror so'z kiriting:");
                sendMessage.setChatId(String.valueOf(chatId));
                sendMessage.setReplyMarkup(botService.markup());
            }else if (text.equals("Russian - English")){
                userState = UserState.RUENG;
                botService.setUserSate(user,userState);
                sendMessage.setText("Siz rus tilidan ingliz tiliga tarjima qilishingiz mumkin. Biror so'z kiriting:");
                sendMessage.setChatId(String.valueOf(chatId));
                sendMessage.setReplyMarkup(botService.markup());
            }else if (text.equals("Turkish - English")){
                userState = UserState.TRENG;
                botService.setUserSate(user,userState);
                sendMessage.setText("Siz turk tilidan ingliz tiliga tarjima qilishingiz mumkin. Biror so'z kiriting:");
                sendMessage.setChatId(String.valueOf(chatId));
                sendMessage.setReplyMarkup(botService.markup());
            }else if (text.equals("English - Turkish")){
                userState = UserState.ENGTR;
                botService.setUserSate(user,userState);
                sendMessage.setText("Siz inliz tilidan turk tiliga tarjima qilishingiz mumkin. Biror so'z kiriting:");
                sendMessage.setChatId(String.valueOf(chatId));
                sendMessage.setReplyMarkup(botService.markup());
            }else if (text.equals("Russian - Turkish")){
                userState = UserState.RUTR;
                botService.setUserSate(user,userState);
                sendMessage.setText("Siz rus tilidan turk tiliga tarjima qilishingiz mumkin. Biror so'z kiriting:");
                sendMessage.setChatId(String.valueOf(chatId));
                sendMessage.setReplyMarkup(botService.markup());
            }else if (text.equals("Turkish - Russian")){
                userState = UserState.TRRU;
                botService.setUserSate(user,userState);
                sendMessage.setText("Siz turk tilidan rus tiliga tarjima qilishingiz mumkin. Biror so'z kiriting:");
                sendMessage.setChatId(String.valueOf(chatId));
                sendMessage.setReplyMarkup(botService.markup());
            }else {
                Translate translate = botService.getTranslate(text,userState.function());
                if (translate != null){
                    Def[] defs = translate.getDef();
                    if (defs[0] != null) {
                        Tr[] trs = defs[0].getTr();
                        if (trs[0] != null) {
                            sendMessage.setText(trs[0].getText());
                            sendMessage.setChatId(String.valueOf(chatId));
                            sendMessage.setReplyMarkup(botService.markup());
                        }else{
                            sendMessage.setText("Xatolik");
                            sendMessage.setChatId(String.valueOf(chatId));
                            sendMessage.setReplyMarkup(botService.markup());
                        }
                    }else{
                        sendMessage.setText("Xatolik");
                        sendMessage.setChatId(String.valueOf(chatId));
                        sendMessage.setReplyMarkup(botService.markup());
                    }
                }else{
                    sendMessage.setText("Xatolik");
                    sendMessage.setChatId(String.valueOf(chatId));
                    sendMessage.setReplyMarkup(botService.markup());
                }
            }
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return USERNAME;
    }

    @Override
    public String getBotToken() {
        return TOKEN;
    }
}
