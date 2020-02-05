public class X {


    int x;

    public synchronized void waitFor(int min, int max) throws IllegalArgumentException {


        if (min > max) {
            throw new IllegalArgumentException();
        }
        while (!(x >= min && x <= max)) {

            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public synchronized int getX() {
        return x;
    }

    public synchronized void setX(int x) {
        this.x = x;
        notifyAll();
    }
}
