import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Bot extends TelegramLongPollingBot {
    private static String nameBot = "MyTestBotStaferov_bot";
    private static String tokenBot = "1109880785:AAGlDe6cSvxU0taITHnlS6Avaa5x7UwMApA";

    public static void main(String[] args) {
        ApiContextInitializer.init();//start
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();//initialize
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text) {//send message to user with his name and to this msg
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);//
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);

        try {
//            setButtons(sendMessage);
            execute(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

//    public void setButtons(SendMessage sendMessage) {//keyboard create
//        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//        sendMessage.setReplyMarkup(replyKeyboardMarkup);
//        replyKeyboardMarkup.setSelective(true);
//        replyKeyboardMarkup.setResizeKeyboard(true);
//        replyKeyboardMarkup.setOneTimeKeyboard(false);
//
//
//        List<KeyboardRow> keyboardRowList = new ArrayList<>();
//        KeyboardRow keyboardFirstRow = new KeyboardRow();
//
//        keyboardFirstRow.add(new KeyboardButton("/help"));
//        keyboardFirstRow.add(new KeyboardButton("/setting"));
//
//        keyboardRowList.add(keyboardFirstRow);
//        replyKeyboardMarkup.setKeyboard(keyboardRowList);
//
//    }

    public void onUpdateReceived(Update update) {
        Model model = new Model();
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
//                case "/help":
//                    sendMsg(message, "Чем могу помочь?");
//                    break;
//                case "/setting":
//                    sendMsg(message, "Пока что нечего настраивать =)");
//                    break;
                default:
                    try {
                        sendMsg(message, Weather.getWeather(message.getText(), model));
                    } catch (IOException e) {
                        sendMsg(message, "Город не найден");
                    }
            }
        }
    }

    public String getBotUsername() {
        return nameBot;
    }
    public String getBotToken() {
        return tokenBot;
    }
}
