package lesson1;

public abstract class User {
    private int id;
    private String name;
    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age > 0) {
            this.age = age;
        }
        //complex logic
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public User() {
    }

    public User(int id) {
        this(id, "default name");
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBefore18() {
        if (age >= 18) {
            return 0;
        } else {
            return 18 - age;
        }
    }

    String info() {
        return this.toString();
    }

    public abstract void doAction();
}
