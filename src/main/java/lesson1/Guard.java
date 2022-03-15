package lesson1;

public class Guard {
    boolean canPass(User user) {
        return user.getBefore18() == 0;
    }
}
