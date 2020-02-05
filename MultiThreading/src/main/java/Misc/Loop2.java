package Misc;

public class Loop2 extends Thread {

    public Loop2(String name) {

        super((name));
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {

            System.out.println(getName() + ": " + i);
        }
    }

    public static void main(String[] args) {

        // Hier werden jetzt drei neue Thread-Objekte vom typ "Misc.Loop2" erzeugt. Misc.Loop2-Objekte können ja jetzt auch zu Threads gemacht werden - durch "extends Thread"
        Loop2 t1 = new Loop2("Thread 1"); // Der hier angegebene String wird bei der Erzeugung des Threads an dessen Konstruktor als Thread-Name übergeben.
        Loop2 t2 = new Loop2("Thread 2");
        Loop2 t3 = new Loop2("Thread 3");

        t1.start();
        t2.start();
        t3.start();
    }
}
