package homework2.interfaces;

public interface Jumpable extends Members {
    /**
     * Контракт на то, что класс реализующий данный интерфейс умеет прыгать
     * @return возвращает целое число - на какую высоту может прыгнуть объект за раз в сантиметрах
     */
    public int jump();
}
