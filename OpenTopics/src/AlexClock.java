// begin alex solution

public class Main {


    public static void main(String[] args) {
        System.out.println("Hello World!");
        LogicalTime logicalTime = new LogicalTime();

        Clock clock_01 = new Clock(logicalTime, 25);
        Waiter wait01 = new Waiter(logicalTime, 5, "waiter 1");
        Waiter wait02 = new Waiter(logicalTime, 15, "waiter 2");
        Waiter wait03 = new Waiter(logicalTime, 20, "waiter 3");

        clock_01.start();
        wait01.start();
        wait02.start();
        wait03.start();


    }
}

class Clock extends Thread {
    private LogicalTime logicalClock;
    private int clockTicks;

    // this will call the logicaltimer to increase the tick
    public Clock(LogicalTime logicalClock, int clockTicks) {
        this.logicalClock = logicalClock;
        this.clockTicks = clockTicks;
    }

    public void run() {
        System.out.println("running clock");
        try {
            for (int i = clockTicks; i > 0; i--) {
                logicalClock.tick();
                this.sleep(1000); // sleep a second
            }
        } catch (Exception e) {

        }
    }

}

class Waiter extends Thread {
    private LogicalTime logicalClock;
    private int waitTicks;
    private String name = "";

    public Waiter(LogicalTime logicalClock, int waitTicks, String name) {
        this.logicalClock = logicalClock;
        this.waitTicks = waitTicks;
        this.name = name;
    }

    // when ever notifyAll is called this method will be called, started from its last current execution state,
    // hence continuing the loop within waitTicks method
    public void run() {
        System.out.println(name + " starting to wait...");
        logicalClock.waitTicks(waitTicks);
        System.out.println(name + " is done waiting");
        // do something...
    }


}


 class LogicalTime {

    // this is the global tick counter, shared by all threads
    // so it needs to be static, otherwise each instance has its own tick
    private static int ticks = 0;

    public synchronized void tick() {

        // why decrease? shouldnt the clock run forward?
        //ticks--;
        ticks++;
        System.out.println("Clock is: " + ticks);

        // notify threads that clock has ticked
        notifyAll();
    }

    public synchronized void waitTicks(int waitingTicks) {
        System.out.println("waiting for " + waitingTicks + " ticks");
        // do not override var tick since it is global and thus shared by all threads
        //int ticks = waitingTicks;

        // get the value to wait for
        // the logic is: we need to wait until a certain amount t of ticks have passed.
        // we calculate its absolute value here and wait until the clock has reached it
        int endTicks = getTicks() + waitingTicks;
        System.out.println("tick goal is: " + endTicks);

        // we wait until enough ticks have passed,
        while (getTicks() < endTicks) {
            System.out.println("WAITING...");
            try {
                wait();
            } catch (Exception e) {

            }
        }
        System.out.println("waited long enough");
        // according to problem statement, only other threads should set the time. hence this is wrong
        //tick();

    }

    // do not access var tick directly
    private synchronized int getTicks(){
        return ticks;
    }
}

