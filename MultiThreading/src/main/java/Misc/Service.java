package Misc;

public class Service implements Runnable {

    private boolean[] array;
    private int start;
    private int end;
    private int result;

    public Service(boolean[] array, int start, int end) {

        this.array = array;
        this.start = start;
        this.end = end;

    }

    public int getResult() {

        return result;
    }

    public void run() {

        for (int i = start; i <= end; i++) {

            if (array[i]) {

                result++;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class AsynchRequest {

    private static final int ARRAY_SIZE = 10000;
    private static final int WORKER_COUNT = 10;

    public static void main(String[] args) {

        //Erzeuge Feld bei dem jeder 10. Wert true ist.
        boolean[] array = new boolean[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {

            if (i % 10 == 0) {

                array[i] = true;
            } else {
                array[i] = false;
            }
        }

        // STATS
        long startTime = System.currentTimeMillis();

        // Feld für Services und Threads erzeugen
        Service[] service = new Service[WORKER_COUNT];
        Thread[] worker = new Thread[WORKER_COUNT];

        // Threads erzeugen
        int start = 0;
        int end;
        int howMany = ARRAY_SIZE / WORKER_COUNT;

        for (int i = 0; i < WORKER_COUNT; i++) {

            if (i < WORKER_COUNT - 1) {

                end = start + howMany - 1;

            } else {
                end = ARRAY_SIZE - 1;
            }
            service[i] = new Service(array, start, end);
            worker[i] = new Thread(service[i]);
            worker[i].start();
            start = end + 1;

        }

        // Sync mit Workers
        for (int i = 0; i < WORKER_COUNT; i++) {

            try {
                worker[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Gesamtergebnis aus Teilergebnissen berechnen
        int result = 0;
        for (int i = 0; i < WORKER_COUNT; i++) {

            result += service[i].getResult();

        }

        // Laufzeit messen
        long calculationTime = System.currentTimeMillis();
        float time = (calculationTime - startTime) / 1000.0f;
        System.out.println("Rechenzeit: " + time);
    }
}
