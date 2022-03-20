package homework2.obstacles;

import homework2.interfaces.Jumpable;
import homework2.interfaces.Members;
import homework2.interfaces.Obstacles;


public class Wall extends Obstacles {
    /**
     * Высота препятствия в сантиметрах
     */
    private final int height;

    public int getHeight() {
        return height;
    }

    public Wall(int height) {
        if (height <= 0 && height > 100000) {
            System.out.println("Значение указанно не верное. Значение заменено на значение по умолчанию = '1' !");
            height = 1;
        }
        this.height = height;
    }

    @Override
    public boolean passing(Members obj) {
        if (((Jumpable) obj).jump() >= height) {
            System.out.println("Успешно перепрыгнул на высоту препятствия - " + height + "см. !");
            return true;
        } else {
            System.out.println("Не смог прыгнуть на высоту препятствия - " + height + "см. !");
            return false;
        }
    }
}
