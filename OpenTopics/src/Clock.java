public class Clock extends Thread {

    private LogicalTime logicalClock;

    public Clock(LogicalTime logicalClock) {

        this.logicalClock = logicalClock;

    }

    public void run() {

        this.logicalClock.waitTicks(5);

    }
}
