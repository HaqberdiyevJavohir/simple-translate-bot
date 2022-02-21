package botElements;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public interface BotUtils {
    String key = "your_yandex_translate_key";
    String TOKEN = "your_telegram_bot_token";
    String USERNAME = "your_telegram_bot_username";

    default ReplyKeyboardMarkup markup(){
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);

        List<KeyboardRow> rows = new ArrayList<>();

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add("English - Russian");
        rows.add(keyboardRow);

        keyboardRow = new KeyboardRow();
        keyboardRow.add("Russian - English");
        rows.add(keyboardRow);

        keyboardRow = new KeyboardRow();
        keyboardRow.add("Turkish - English");
        rows.add(keyboardRow);

        keyboardRow = new KeyboardRow();
        keyboardRow.add("English - Turkish");
        rows.add(keyboardRow);

        keyboardRow = new KeyboardRow();
        keyboardRow.add("Russian - Turkish");
        rows.add(keyboardRow);

        keyboardRow = new KeyboardRow();
        keyboardRow.add("Turkish - Russian");
        rows.add(keyboardRow);

        markup.setKeyboard(rows);
        return markup;
    }
}
