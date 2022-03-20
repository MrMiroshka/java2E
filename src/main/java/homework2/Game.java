package homework2;

import homework2.interfaces.Members;
import homework2.interfaces.Obstacles;
import homework2.members.Cat;
import homework2.members.Human;
import homework2.members.Robot;
import homework2.obstacles.RunningTrack;
import homework2.obstacles.Wall;

import java.util.ArrayList;
import java.util.Random;

public class Game {
    private Random random = new Random();
    private Obstacles[] obstacles;
    private Members[] members;
    private ArrayList<Members> finished;

    /**
     * Генерирует набор различных участников
     *
     * @param count - количество участников
     */
    public void generateMembers(int count) {
        members = new Members[count];
        for (int i = 0; i < count; i++) {
            switch (random.nextInt(3)) {
                case (0):
                    members[i] = new Cat.Builder()
                            .name("Cat " + (i + 1))
                            .height(random.nextInt(400) + 1)
                            .weight(random.nextInt(40000) + 1)
                            .build();
                    break;
                case (1):
                    members[i] = new Human.Builder()
                            .name("Human " + (i + 1))
                            .height(random.nextInt(400) + 1)
                            .weight(random.nextInt(40000) + 1)
                            .build();
                    break;
                case (2):
                    members[i] = new Robot.Builder()
                            .name("Robot " + (i + 1))
                            .height(random.nextInt(400) + 1)
                            .weight(random.nextInt(40000) + 1)
                            .build();
                    break;
            }
        }
    }

    /**
     * Генерирует набор различных припятствий
     *
     * @param count - количество припятствий
     */
    public void generateObstacles(int count) {
        obstacles = new Obstacles[count];
        for (int i = 0; i < count; i++) {
            switch (random.nextInt(2)) {
                case (0):
                    obstacles[i] = new RunningTrack(random.nextInt(20000) + 1);
                    break;
                case (1):
                    obstacles[i] = new Wall(random.nextInt(300) + 1);
                    break;
            }
        }
    }

    /**
     * Инициализация игры. Автоматически генерирует препятствия и участников
     *
     * @param countMembers  - количество участников
     * @param counObstacles - количество препятствий
     */
    public Game(int countMembers, int counObstacles) {
        generateMembers(countMembers);
        generateObstacles(counObstacles);
        finished = new ArrayList<>();
    }

    /**
     * Гланый метод логики игры (n участников проходят m припятствий)
     * P.S.: есть допущение, для каждого нового припятствия у участника не уменьшаются возможности (всегда может пробежать ажем 400 метров)
     */
    public void start() {
        boolean flagFinished = true;
        for (Members member : members) {
            for (int i = 0; i < obstacles.length; i++) {
                System.out.println("Участник " + member.getName() + "- приступает к припятствию под номером - " + (i + 1));
                if (!obstacles[i].passing(member)) {
                    printLabel("Участник выбывает!");
                    flagFinished = false;
                    break;
                }
            }
            if (flagFinished) {
                finished.add(member);
                printLabel("Участник прошел все препятствия!");
            }
        }

        printLabel("Игра завершена!");

        finish();
    }

    /**
     * Выводит итоги игры
     */
    private void finish() {
        if (finished.size() > 0) {
            printLabel("Прошли все припятствия следующие участники:");
            for (Members member : finished) {
                System.out.println(member.getName());
            }
            System.out.println();
        } else {
            printLabel("Никто не прошел ВСЕ припятствия!");
        }
    }

    private void printLabel(String str) {
        System.out.println();
        System.out.println("*****************************************************");
        System.out.println("      " + str);
        System.out.println("*****************************************************");
        System.out.println();
    }

}
