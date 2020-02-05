package Misc;

public class ThreadLauncher {

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            myThread thread = new myThread();
            thread.start();
        }
    }
}
