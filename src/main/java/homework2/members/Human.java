package homework2.members;

import homework2.interfaces.Jumpable;
import homework2.interfaces.Runnable;

public class Human implements Jumpable, Runnable {
    /**
     * name - имя человека
     */
    private final String name;

    /**
     * height - на какую высоту может прыгнуть человек (в сантиметрах)
     */
    private final int height;

    /**
     * weight - сколько может пробежать человек (в метрах)
     */
    private final int weight;

    private Human(Builder builder) {
        this.name = builder.name;
        this.height = builder.height;
        this.weight = builder.weight;
    }

    @Override
    public int jump() {
        System.out.println("Я человек, меня зовут  - '" + this.name + "' , я прыгаю");
        return this.height;
    }

    @Override
    public int run() {
        System.out.println("Я человек, меня зовут  - '" + this.name + "' , я бегу");
        return this.weight;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }


    public int getWeight() {
        return weight;
    }


    public static class Builder {
        private String name;
        private int height;
        private int weight;

        public Builder name(String name) {
            if (name != null && !name.isEmpty()) {
                this.name = name;
            } else {
                System.out.println("Не верно указали имя");
            }
            return this;
        }

        public Builder height(int height) {
            if (height > 0 && height < 600) {
                this.height = height;
            } else {
                System.out.println("Не верное значение высоты прыжка");
            }
            return this;
        }

        public Builder weight(int weight) {
            if (weight > 0 && weight < 100000) {
                this.weight = weight;
            } else {
                System.out.println("Не верное значение расстояния бега");
            }
            return this;
        }

        public Human build() {
            return new Human(this);
        }
    }

}
