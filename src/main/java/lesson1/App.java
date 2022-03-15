package lesson1;

public class App {

    public static void main(String[] args) {
        //User user = new User();
        User user1 = new ComputerUser();
        ComputerUser user2 = new ComputerUser();
        Guard guard = new Guard();
        boolean canPass = guard.canPass(user2);
    }

}
