package homework2.interfaces;

public interface Runnable extends Members {
    /**
     * Контракт на то, что класс реализующий данный интерфейс умеет бегать
     * @return ворзвращает целое число - сколько может пробежать объект за раз в метрах
     */
    public int run ();
}
