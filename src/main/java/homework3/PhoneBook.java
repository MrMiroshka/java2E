package homework3;

import homework2.members.Cat;
import jdk.jfr.StackTrace;

import java.util.*;

public class PhoneBook {
    private final Map<String, Set<String>> phoneDictionary;
    private final Set<Character> NUMBER = Set.of('+', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0');
    private final Set<Character> SNAME = Set.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z');

    public PhoneBook() {
        this.phoneDictionary = new HashMap<>();
    }

    /**
     * Добавляем Фамилию и Номер телефона в словарь
     *
     * @param secondName  Фамилия
     * @param phoneNumber Номер телефона
     * @return
     */
    public Boolean add(String secondName, String phoneNumber) {
        Boolean flag = false;
        final StringBuilder extString = new StringBuilder();
        for (Character ch : secondName.toCharArray()) {
            if (!SNAME.contains(ch)) {
                extString.append(secondName + " - " + "В имени допущена ошибка ! ");
                flag = true;
                break;
            }
        }

        for (Character ch : phoneNumber.toCharArray()) {
            if (!NUMBER.contains(ch)) {
                extString.append(phoneNumber + " - " + "В номере телефона допущена ошибка!");
                flag = true;
                break;
            }
        }

        if (flag) {
            try {
                throw new MyExceptionWrongData(extString.toString());
            } catch (Exception e) {
                //не будем разрабатывать систему логов)) выведим просто инфу об ошибки в консоль
                e.printStackTrace(System.out);
            }
            return false;
        }

        final Set<String> phone = phoneDictionary.get(secondName) != null ? phoneDictionary.get(secondName) : new HashSet<>();
        phone.add(phoneNumber);
        phoneDictionary.put(secondName, phone);
        return true;

    }

    public Set<String> get(String secondName) {
        return phoneDictionary.get(secondName);
    }
}
