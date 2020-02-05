package Misc;

public class StopThread extends Thread {

    private boolean stopped = false;

    public StopThread() {

        start();
    }

    @Override
    public void run() {
        int i = 0;
        try {


            while (!isInterrupted()) {

                i++;
                System.out.println("Running Counter: " + i);
                Thread.sleep(100);

            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Terminating Thread . . .");
    }

    public static void main(String[] args) {

        StopThread stopThread = new StopThread();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stopThread.interrupt();
    }
}
