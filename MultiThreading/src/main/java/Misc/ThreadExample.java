package Misc;

public class ThreadExample {

    public static void main(String[] args) {

        Sleeper sleeper = new Sleeper();
        DataInput dataInput = new DataInput();
        ThreadExample threadExample = new ThreadExample();
        sleeper.start();
        dataInput.start();
    }
}
