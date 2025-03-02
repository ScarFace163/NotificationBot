package backend.academy.bot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;

@Service
public class OutputService {

    private final TelegramBot telegramBot;

    public OutputService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public void sendMessage(SendMessage message) {
        telegramBot.execute(message);
    }
}
