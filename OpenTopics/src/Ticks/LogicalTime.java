package Ticks;

public class LogicalTime {

    private int ticks = 0;

    public synchronized void tick() {

        ticks++;
        notifyAll();
    }

    public synchronized void waitTicks(int waitingTicks) {

        while (waitingTicks > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitingTicks--;
        }
    }
}