package parServers;

import java.io.IOException;
import java.net.ServerSocket;

public class TCP_Par_CounterServer { // Das hier ist ein statisch paralleler TCP Server, der mit einer externen Worker- / Thread-Klasse arbeitet. / Aus der Vorlesung.

    public static final int DEFAULT_PORT = 1250;
    public static final int WORKER_COUNT = 4; //Anzahl der verwendeten Threads


    public static void main(String[] args) throws IOException {

        // Hier wird nur noch da erzeugt, was die Worker zum arbeiten brauchen:

        final SynchronizedCounter synchronizedCounter = new SynchronizedCounter();
        final ServerSocket serverSocket = new ServerSocket(TCP_Par_CounterServer.DEFAULT_PORT);

        for (int i = 0; i < TCP_Par_CounterServer.WORKER_COUNT; i++) {

            final WorkerThread worker = new WorkerThread(serverSocket, synchronizedCounter);
            worker.start(); // Bitte jetzt diesn Thread ausführen.
            System.out.println("Worker " + i+1 + " has been created and is running.");

        }
    }
}

/*
 Bei bis zur Anzahl der WORKER_COUNT Clients werden alle Anfragen parallel abgearbeitet. Ab wt+1 Anfragen kommen diese
 in eine Warteschlage und müssen warten, bis wieder ein Worker frei wird.
*/
