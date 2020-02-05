package Misc;

public class Sleeper extends Thread {

    public void run() {

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            System.err.println("Thread was unexpectedly interrupted.");
            e.printStackTrace();
        }
    }
}
