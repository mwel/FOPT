package Ticks;

public class ClockLauncher {

    public static void main(String[] args) {

        LogicalTime logicalTime = new LogicalTime();

        Clock clock_01 = new Clock(logicalTime);
        Clock clock_02 = new Clock(logicalTime);
        Clock clock_03 = new Clock(logicalTime);
        clock_01.start();
        clock_02.start();
        clock_03.start();

    }
}
