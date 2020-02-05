package Misc;

public class Loop1 extends Thread {

    private String myName;

    public Loop1(String name) {

        myName = name;
    }

    public void run() {

        for (int i = 0; i < 100; i++) {

            System.out.println("Name: " + i);


        }
    }

    public static void main(String[] args) {
        Loop1 t1 = new Loop1("Thread 1");
        Loop1 t2 = new Loop1("Thread 1");
        Loop1 t3 = new Loop1("Thread 1");

        t1.start();
        t2.start();
        t3.start();

    }


}
