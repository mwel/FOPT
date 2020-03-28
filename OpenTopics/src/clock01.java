import java.text.SimpleDateFormat;
import java.util.Date;

public class clock01 {

    int ticksToWait = 0;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");


    public synchronized void tick() {

        Date date = new Date(System.currentTimeMillis());
        System.out.println("Before notifyAll " + formatter.format(date));

        ticksToWait--;
        notifyAll();

        Date date2 = new Date(System.currentTimeMillis());
        System.out.println("After notifyAll " + formatter.format(date2));
        System.out.println("TicksToWait (notifyAll): " + ticksToWait);
    }

    public void waitingTicks(int waitTicks) {

        ticksToWait = waitTicks;
        while (ticksToWait > 0) {

            Date date3 = new Date(System.currentTimeMillis());
            System.out.println("Before Wait " + formatter.format(date3));
            Date date4 = new Date(System.currentTimeMillis());
            System.out.println("After Wait " + formatter.format(date4));
            System.out.println("TicksToWait (wait): " + ticksToWait);

        }
    }


    public static void main(String[] args) {

        clock01 clock = new clock01();

        Thread waiter = new Thread(() -> {

            clock.waitingTicks(20);
        });
        waiter.start();

        for (int i = 0; i < 30; i++) {

            Thread ticker = new Thread(() -> {

                clock.tick();
            });
            ticker.start();
        }
    }
}