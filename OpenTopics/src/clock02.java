public class clock02 extends Thread {

    private int time;
    private int ticks;

    public synchronized void tick() {

        ticks++;
        notifyAll();
    }

    public synchronized void waitFor(int ticks) {

        if (ticks < 0) {
            throw new IllegalArgumentException();
        }

        int count = time + ticks;
        while (count != time) {

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count++;
        }
    }
}