package lesson1.interfaces;

public class App {
    public static void main(String[] args) {
        Student student = new Student();
        Pupil pupil = new Pupil();

        Studyable[] studyables = new Studyable[]{student, pupil};

        for (Studyable studyable : studyables) {
            studyable.study();
        }
    }
}
