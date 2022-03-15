package lesson1.interfaces;

public class Pupil implements Studyable, Workable{
    @Override
    public void study() {
        System.out.println("Ученик учится");
    }

    @Override
    public void work() {
        System.out.println("Студент еще и работает!");
    }
}
