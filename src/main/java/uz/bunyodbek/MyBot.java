package uz.bunyodbek;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageMedia;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MyBot extends TelegramLongPollingBot {

    private MyBotService myBotService = new MyBotService();

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()){

            Long chatId = update.getMessage().getChatId();
            String text = update.getMessage().getText();


            if (text.equals("/start")){

                try {
                    execute(myBotService.sendMessage(chatId));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }

            }
            if (text.equals("ShareContact")){
                try {
                    execute(myBotService.inlineMessage(chatId));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }





        }
        else if (update.hasCallbackQuery()){

            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();
            Long chatId = callbackQuery.getMessage().getChatId();

            if (data.equals("telefonid")){
                try {
                    execute(myBotService.sendPhotomain(chatId));
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }


            if (data.equals("telefonid")){

                EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
                editMessageReplyMarkup.setChatId(chatId);
                editMessageReplyMarkup.setMessageId(callbackQuery.getMessage().getMessageId());
                editMessageReplyMarkup.setReplyMarkup(myBotService.sendPhoto(chatId));

                try {
                    execute(editMessageReplyMarkup);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }
            if (data.equals("buyid")){

                EditMessageReplyMarkup editMessageReplyMarkup = new EditMessageReplyMarkup();
                editMessageReplyMarkup.setChatId(chatId);
                editMessageReplyMarkup.setMessageId(callbackQuery.getMessage().getMessageId());
                editMessageReplyMarkup.setReplyMarkup(myBotService.menuPul(chatId));

                try {
                    execute(editMessageReplyMarkup);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }


            }



        }



    }

    @Override
    public String getBotUsername() {
        return "javatelegramBot11BBot";
    }

    @Override
    public String getBotToken() {
        return "7958317763:AAE83Vm2Pgmuup3DizemzsRB87YpDQyp5oI";
    }
}
