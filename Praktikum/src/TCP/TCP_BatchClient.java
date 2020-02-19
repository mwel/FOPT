package TCP;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCP_BatchClient {

    private static int batchSize = 10;
    private static int replyCount = 0;
    private static int PORT = 1250;

    public static void main(String[] args) {

        InetAddress serverAddress = null;
        try {
            serverAddress = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try (Socket socket = new Socket(serverAddress, PORT)) {

            for (int i = 0; i < batchSize; i++) {

                String outMessage = "-- OUT -- Request [" + i + "] to server " + serverAddress + ":" + PORT;

                final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                writer.write(outMessage);
                writer.newLine();
                writer.flush();
            }

            for (int i = 0; i < batchSize; i++) {

                final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String inMessage = reader.readLine();
                replyCount++;
                System.out.println("--  IN -- Reply [" + replyCount + "]:" + inMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Socket closed.");
    }
}
