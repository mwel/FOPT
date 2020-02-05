package seqServers;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCP_SimpleServer_nRequests { // Server für beliebig viele Client Requests // Zeilen => 2019 Klausur Lösung

    public static void main(String[] args) throws IOException {

        try (final ServerSocket serverSocket = new ServerSocket(1250)) {
            while (true) {
                try (final Socket socketToClient = serverSocket.accept()) {
                    TCP_SimpleServer_nRequests.processConnection(socketToClient);
                }
            }
        }
    }

    private static void processConnection(final Socket socket) throws IOException {

        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

        String request = null;

        while ((request = bufferedReader.readLine()) != null) {

            bufferedWriter.write("Echo: " + request);

            bufferedWriter.newLine();

            bufferedWriter.flush(); /* hier flushen wir nach jedem Durchlauf. Müssen wir, weil es darauf ankommt,
            wie der Client aufgebaut ist. Falls der Client bei jeder Anfrage auf eine Antwort wartet,
            dann brauchen wir das. Falls wir das weglassen, könnte es für den Client so aussehen,
            als würden wir ihm gar nicht antworten. */
        }
    }
}
