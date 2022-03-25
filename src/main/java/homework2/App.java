package homework2;

/**
 * Домашнее задание №1 курс Java 2
 * @author Miroshnichenko Igor
 * @version 1.1
 * created on 2022-03-20
 */
public class App {
    public static void main(String[] args) {
        //создаем игру и генерируем 5 участников и 13 припятствий
        Game game = new Game(5,13);
        game.start();
    }
}
