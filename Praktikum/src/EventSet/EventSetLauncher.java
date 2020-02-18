package EventSet;

public class EventSetLauncher {

    public static void main(String[] args) throws InterruptedException {
        EventSet eSet = new EventSet(5);
        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1 : Waiting for one value to get true.");
            eSet.waitOR();
            System.out.println("Thread 1 : At least one value changed to true.");
        });

        Thread t2 = new Thread(() -> {
            System.out.println("Thread 2 : Waiting for all values to get true.");
            eSet.waitAND();
            System.out.println("Thread 2 : All values changed to true.");
        });

        t1.start();
        t2.start();

        Thread.sleep(400);

        eSet.set(1, true);

        Thread.sleep(800);

        for (int i = 0; i < 5; i++) {
            System.out.println(i);
            eSet.set(i, true);
            Thread.sleep(300);
        }

    }
}


