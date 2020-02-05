package CarValues;

public class Consumer extends Thread {

    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {

        for (int i = 0; i < 100; i++) {

            int x = buffer.get();
            System.out.println("Read value: " + i);
        }
    }
}
