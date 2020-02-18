package BoundedCounter;

public class BoundedCounter extends Thread {

    private int count;
    private int min;
    private int max;

    public BoundedCounter(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException();
        }
        this.min = min;
        this.max = max;
        this.count = min;
    }

    public synchronized void up() {

        while (count >= max) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        notifyAll();
    }

    public synchronized void down() {

        while (count <= min) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        notifyAll();
    }

    public synchronized int getCount() {

        return count;
    }
}

