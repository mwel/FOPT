package Misc;

public class Loop3 extends Thread {

    public Loop3(String name) {

        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {

            System.out.println(getName() + ": " + i);

            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {

        Loop3 t1 = new Loop3("Thread 1");
        Loop3 t2 = new Loop3("Thread 2");
        Loop3 t3 = new Loop3("Thread 3");

        t1.start();
        t2.start();
        t3.start();

    }
}
