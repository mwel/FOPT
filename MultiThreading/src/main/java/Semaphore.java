public class Semaphore {

    private int value; // Dieser Wert darf nicht negativ werden.

    public Semaphore(int init) {

        if (init < 0) {
            throw new IllegalArgumentException("Parameter < 0");
        }
        value = init;
    }

    public synchronized void p() {

        while (value == 0) {

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            value--;
        }
    }

    public synchronized void v() {
        value++;
        notify();
    }
}
