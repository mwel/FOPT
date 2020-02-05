package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

//     << HST | TCP Client Lösung für beliebig viele Zeilen / Anfragen vom Client >>

public class TcpServer {

    public static void main(String[] args) throws IOException {

        try (final ServerSocket serverSocket = new ServerSocket(1250)) {
            System.out.println("[SERVER] Server created and waiting.");

            while (true) {
                try (final Socket TCPSocket = serverSocket.accept()) {

                    System.out.println("[SERVER] Request accepted.");

                    // Reihenfolge super wichtig:
                    final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(TCPSocket.getInputStream(), StandardCharsets.UTF_8));
                    final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(TCPSocket.getOutputStream(), StandardCharsets.UTF_8));

                    String request = null;
                    while ((request = bufferedReader.readLine()) != null) {

                        bufferedWriter.write("[SERVER] Confirmation of message received: " + request);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                }
            }
        }
    }
}