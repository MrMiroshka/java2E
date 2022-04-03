package homework3;

import java.util.ArrayList;
import java.util.Random;

public class SmallDictionary {

    private final String SOURCES = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
    private final Random random = new Random();

    /**
     * Генерирует случайный список слов
     * @param lengthDictionary - длинна словаря
     * @param lengthString - длинна генерируемой строки
     */
    public ArrayList<String> generateUniqueDictionary(int lengthDictionary,int lengthString) {
        ArrayList<String> dictionary = new ArrayList<>(lengthDictionary);
        for (int i = 0; i <lengthDictionary; i++) {
            dictionary.add(generateString(lengthString));
        }
        return dictionary;
    }

    /**
     * Генерирует случайную строку.
     *
     * @param length длинна генерируемой cтроки.
     * @return
     */
    private String generateString(int length) {
        char[] text = new char[length];
        for (int i = 0; i < length; i++) {
            text[i] = SOURCES.charAt(random.nextInt(SOURCES.length()));
        }
        return new String(text);
    }
}
