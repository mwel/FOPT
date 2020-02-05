package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class myClient {

    public static void main(String[] args) {
        myClient client = new myClient("localhost", 8000);
        client.sendMessage("[Client]: Is this working at all?");
    }

    private InetSocketAddress address;

    public myClient(String hostname, int port) {
        address = new InetSocketAddress(hostname, port);
    }

    public void sendMessage(String msg) {

        try {
            System.out.println("[Client] Connecting to server...");
            Socket socket = new Socket();
            socket.connect(address, 5000);
            System.out.println("[Client] Connected");

            System.out.println("[Client] Sending message...");
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            pw.println(msg);
            pw.flush();
            System.out.println("[Client] Message sent.");

            Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(socket.getInputStream())));
            if (scanner.hasNextLine()) {
                System.out.println("[Client] Answer from Server: " + scanner.nextLine());
            }

            // Close Connection
            pw.close();
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
