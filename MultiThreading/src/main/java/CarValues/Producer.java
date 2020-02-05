package CarValues;

public class Producer extends Thread {

    private Buffer buffer;

    private int start;

    public Producer(Buffer buffer, int start) {
        this.buffer = buffer;
        this.start = start;
    }

    public void run() {

        for (int i = 0; i < start; i++) {

            buffer.put(i);
        }

    }
}
