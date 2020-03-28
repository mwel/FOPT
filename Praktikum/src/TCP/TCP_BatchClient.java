package TCP;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCP_BatchClient {

    private static int batchSize = 1000;
    private static int sentCount = 0;
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

            String resetMessage = "reset";
            String incrementMessage = "increment";

            final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            writer.write(resetMessage);
            writer.newLine();
            writer.flush();

            for (int i = 0; i < batchSize; i++) {
                writer.write(incrementMessage);
                writer.newLine();
                writer.flush();
                sentCount++;
                System.out.println("-- OUT -- Request [" + incrementMessage + "] to " + serverAddress + ":" + PORT);

            }

            for (int i = 0; i < batchSize; i++) {

                final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String inMessage = reader.readLine();
                replyCount++;
                System.out.println("--  IN -- Counter at [" + replyCount + "]:" + inMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Socket closed.");
    }
}
