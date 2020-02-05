public class LogicalTime {

    int ticks = 0;

    public synchronized void tick() {

        ticks--;
        System.out.println("Clock: " + ticks);
        notifyAll();
    }

    public synchronized void waitTicks(int waitingTicks) {
        int ticks = waitingTicks;

        while (!(ticks >= 0)) {
            try {
                System.out.println("Waiting ...");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        tick();
    }
}