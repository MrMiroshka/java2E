package homework3;

import java.util.*;

/**
 * Домашнее задание №3 курс Java 2
 *
 * @author Miroshnichenko Igor
 * @version 2.2
 * created on 2022-03-04
 */
public class App {
    public static void main(String[] args) {
        //создаем словарь из 20 слов, но так как наш класс генерирует уникальные строки, а нам надо чтобы в словаре были
        // повторяющиеся строки. переопредилим метод через анонимный класс (т.к. данный код нужен единожды)

        final List<String> dictionary = new SmallDictionary() {
            @Override
            public ArrayList<String> generateUniqueDictionary(int lengthDictionary, int lengthString) {
                ArrayList<String> tempDictionary = super.generateUniqueDictionary(lengthDictionary, lengthString);
                tempDictionary.add(tempDictionary.get(2));
                tempDictionary.add(tempDictionary.get(4));
                tempDictionary.add(tempDictionary.get(6));
                tempDictionary.add(tempDictionary.get(8));
                tempDictionary.add(tempDictionary.get(10));
                return tempDictionary;
            }
        }.generateUniqueDictionary(15, 5);

        final Set<String> uniqueDictionary = new HashSet<>(dictionary);

        System.out.println("Первоначальный массив с набором слов:");
        System.out.println(dictionary);
        System.out.println("Массив с уникальным набором слов:");
        System.out.println(uniqueDictionary);


        //Посчитаем сколько раз встречается каждое слово
        final Map<String, Integer> countWordsListStorage = new HashMap<>();
        for (String word : dictionary) {
            int count = countWordsListStorage.containsKey(word) ? countWordsListStorage.get(word) + 1 : 1;
            countWordsListStorage.put(word, count);
        }

        //Выведем что унасполучилось
        for (Map.Entry<String, Integer> stringIntegerEntry : countWordsListStorage.entrySet()) {
            System.out.println(new StringBuilder(stringIntegerEntry.getKey() + " - " + stringIntegerEntry.getValue()).toString());
        }


        //Задание 2
        //сгенерируем данные в Телефонную книгу
        System.out.println();
        System.out.println("Генерируем данные...");
        final PhoneBook myPhoneBook= new PhoneBook();
        myPhoneBook.add("AAA","+790843234");
        myPhoneBook.add("AAA","+790843232");
        myPhoneBook.add("AAA","+790843231");
        myPhoneBook.add("BBB","+790434334");
        myPhoneBook.add("CCC","+79084323g4");
        System.out.println();
        System.out.println("Найдем все номера абонента 'ААА':");
        System.out.println(myPhoneBook.get("AAA"));
    }
}
