package homework3;

public class MyExceptionWrongData extends IllegalArgumentException{
    public MyExceptionWrongData(String s) {
        super(s);
    }
}
