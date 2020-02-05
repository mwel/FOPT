package parServers;

import sockets.TCP_Socket;

import java.io.IOException;
import java.net.ServerSocket;

public class WorkerThread extends Thread {

    private final ServerSocket serverSocket;
    private final SynchronizedCounter counter;

    public WorkerThread(final ServerSocket serverSocket, final SynchronizedCounter counter) {
        this.serverSocket = serverSocket;
        this.counter = counter;

        /* Damit jeder Thread den gleichen Socket und den gleichen Counter verwendet und nicht jeder seinen eigenen,
        werden diese beiden Objekte in den Konstruktor des Workers ausgelagert.*/
    }

    public void run() { // Was aus der Main Methode des Server übrig bleibt wird in die run(9 übertragen.
        // Annahme von Verbindungen, Weitergabe der gelesenen Befehle und Versenden der Antworten
        while (true) { // immer wieder auf dem ServerSocket die accept()-Methode aufrufen - solange, bis eine Verbindung kommt.
            try (final TCP_Socket tcpSocket = new TCP_Socket(this.serverSocket.accept())) { // Verbindung in den TCP-Socket packen
                String request = null;
                while ((request = tcpSocket.receiveLine()) != null) { // die einzelnen Anfragen des Clients einlesen
                    final int answer = this.processRequestAndReturnAnswer(request); // Anfragen bearbeiten
                    this.delay(5000);
                    tcpSocket.sendLine(String.valueOf(answer));
                }
            } catch (final IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    private int processRequestAndReturnAnswer(final String request) {
        // Behandlung der übergebnen Anfrage auf den übergebenen Zähler, Rückgabe der Antwort.

        if (request.equals("increment")) {
            return this.counter.increment();

        } else if (request.equals("reset")) {
            return this.counter.reset();
        }

        throw new RuntimeException("Invalid request " + request);
    }

    private void delay(final long millisToDelay) {
        // sleep Methode mit der wir simulieren, dass der Server ausgelastet waere.
        try {
            Thread.sleep(millisToDelay);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }

    }


}
