package backend.academy.bot.component;

import backend.academy.bot.service.OutputService;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TelegramBotListener implements UpdatesListener {

    private final TelegramBot bot;
    private final OutputService outputService;

    public TelegramBotListener(TelegramBot bot, OutputService outputService) {
        this.bot = bot;
        this.outputService = outputService;
    }

    @PostConstruct
    public void init() {
        bot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        for (Update update : updates) {
            if (update.message() != null && update.message().text() != null) {
                String messageText = update.message().text();
                long chatId = update.message().chat().id();

                SendMessage sendMessage = new SendMessage(chatId, "Вы написали: " + messageText);
                outputService.sendMessage(sendMessage);
            }
        }
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
