package seqServers;

import sockets.TCP_Socket;

import java.io.IOException;
import java.net.ServerSocket;

public class TCP_CounterServer { // mit 2 Schleifen und TWR

    public static final int DEFAULT_PORT = 1250;

    private static int counter;

    public static void main(String[] args) throws IOException {

        counter = 0;

        //create socket
        try (final ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT)) {
            System.out.println("Server running and waiting for requests . . .");

            while (true) { // Schleife 1, die für immer auf Anfragen wartet

                try (final TCP_Socket tcpSocket = new TCP_Socket(serverSocket.accept())) { /* Hier nehmen wir über
                die accept() des oben erzeugten serverSockets die jeweils nächste Verbindungsanfrage eines Clients an.*/

                    String request = null;

                    while ((request = tcpSocket.receiveLine()) != null) { // Schleife 2, die von einer bestehenden Verbindung so lange liest, bis der Client die Verbindung geschlossen hat.

                        final String answer = TCP_CounterServer.processRequestAndReturnAnswer(request); // gibt uns die Antwort zurück
                        tcpSocket.sendLine(answer);

                    }
                }
            }
        }
    }

    private static String processRequestAndReturnAnswer(final String request) {

        if (request.equals("increment")) {
            // perform increment operation
            TCP_CounterServer.counter++;
            System.out.println("Counter incremented.");


        } else if (request.equals("reset")) {
            //perform reset
            TCP_CounterServer.counter = 0;
            System.out.println("Counter reset.");
        }
        //generate answer
        return String.valueOf(TCP_CounterServer.counter);
    }
}

