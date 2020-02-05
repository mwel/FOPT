package seqServers;

import parServers.SynchronizedCounter;
import sockets.TCP_Socket;

import java.io.IOException;
import java.net.ServerSocket;

public class TCP_Seq_CounterServer { // Das hier ist die Vorstufe zum parallelen TCP Server

    public static final int PORT = 1250;

    // Hier sorgen wir dafür, dass es für den Client so aussieht, als sei der Server ausgelastet.
    private static void delay(final long millisToDelay) {

        try {
            Thread.sleep(millisToDelay);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int processRequest(final String request, final SynchronizedCounter counter) {
        // Behandlung der übergebnen Anfrage auf den übergebenen Zähler, Rückgabe der Antwort.

        if (request.equals("increment")) {
            return counter.increment();

        } else if (request.equals("reset")) {
            return counter.reset();
        }

        throw new RuntimeException("Invalid request " + request);
    }

    public static void main(String[] args) throws IOException {
        // Erzeugen des zu verwendenen Zählers
        final SynchronizedCounter counter = new SynchronizedCounter(); //Erzeugt einen Counter, der ja bisher nicht existiert.
        System.out.println("Counter created.");

        try (final ServerSocket serverSocket = new ServerSocket(TCP_Seq_CounterServer.PORT)) { // öffnet sS Objekt.
            System.out.println("Server running and waiting for requests . . . ");
            while (true) {
                // Annahme von Verbindungen, Weitergabe der gelesenen Befehle und Versenden der Antworten
                try (final TCP_Socket tcpSocket = new TCP_Socket(serverSocket.accept())) { // wartet auf eingehende Verbindungen
                    String request = null;
                    while ((request = tcpSocket.receiveLine()) != null) { //liest immer wieder die nächste Anfrage ein
                        final int answer = TCP_Seq_CounterServer.processRequest(request, counter); // schleift Werte durch zur Hilfsmethode oben / Behandlung
                        TCP_Seq_CounterServer.delay(1000); // simuliert Wartezeit auf andere n-Threads
                        tcpSocket.sendLine(String.valueOf(answer)); // schreibt und schickt die Antwort raus
                    }
                }
            }
        }
    }
}
