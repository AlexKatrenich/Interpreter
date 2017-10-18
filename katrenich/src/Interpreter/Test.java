package Interpreter;

/**
 * Created by Admin on 18.10.2017.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(Context.evaluate("sqrt(8*9) + 15").interpret());
    }
}
