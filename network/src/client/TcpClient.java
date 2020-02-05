package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

//     << HST | TCP Client Vorgabe >>

public class TcpClient {

    public static void main(String[] args) throws IOException {

        final InetAddress TCPServer = InetAddress.getByName(args[0]);

        try (final Socket TCPSocket = new Socket(TCPServer, 1250)) {
            System.out.println("[CLIENT] Requesting 3-Way-Handshake.");

            final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(TCPSocket.getOutputStream(), StandardCharsets.UTF_8));

            bufferedWriter.write("[CLIENT] Hi Server, are you there? If so, please send a response!");
            bufferedWriter.newLine();
            bufferedWriter.flush();

            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(TCPSocket.getInputStream(), StandardCharsets.UTF_8));

            final String answer = bufferedReader.readLine();
            System.out.println(answer);

        }
    }
}
