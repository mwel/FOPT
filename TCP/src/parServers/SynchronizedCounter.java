package parServers;

public class SynchronizedCounter { // Kapselung des Counters, damit mehrere Threads auf den Zähler zugreifen können.

    private int counter;

    public synchronized int reset() {

        this.counter = 0;
        return this.counter;
    }

    public synchronized int increment() {

        this.counter++;
        return this.counter;
    }
}

// Hier wird quasi ausgelagert, dass man sich überall lokal um die Synchro des Zählers kümmernmuss.