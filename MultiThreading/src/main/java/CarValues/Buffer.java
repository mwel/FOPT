package CarValues;

public class Buffer {

    private boolean available = false;
    private int data;

    public synchronized void put(int x) {

        while (available) {

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        data = x;
        available = true;
        notifyAll();
    }

    public synchronized int get() {
        while (!available) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        available = false;
        notifyAll();
        return data;
    }
}
