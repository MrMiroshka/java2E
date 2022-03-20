package homework2;

public class App {
    public static void main(String[] args) {
        //создаем игру и генерируем 5 участников и 13 припятствий
        Game game = new Game(5,13);
        game.start();

    }
}
