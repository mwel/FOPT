package Misc;

public class RunnableExample {

    public static void main (String[] args) {

        Thread thread = new Thread( new MyRunnable());

        thread.start();

    }
}
