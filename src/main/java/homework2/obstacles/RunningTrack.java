package homework2.obstacles;

import homework2.interfaces.Members;
import homework2.interfaces.Obstacles;
import homework2.interfaces.Runnable;

public class RunningTrack extends Obstacles {
    /**
     * Длинна дистанции в метрах
     */
    private final int weight;

    public int getWeight() {
        return weight;
    }

    public RunningTrack(int weight) {

        if (weight <= 0 && weight > 100000) {
            System.out.println("Значение указанно не верное. Значение заменено на значение по умолчанию = '1' !");
            weight = 1;
        }

        this.weight = weight;

    }

    @Override
    public boolean passing(Members obj) {
        if (((Runnable) obj).run() >= weight) {
            System.out.println("Успешно пробежал препятствие на расстояние - " + weight + "м. !");
            return true;
        } else {
            System.out.println("Не смог пробежать препятствие на расстояние - " + weight + "м. !");
            return false;
        }
    }

}
