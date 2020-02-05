package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class myServer {

    public static void main(String[] args) {
        myServer server = new myServer(8000);
        server.startListening();

    }

    private int port;

    public myServer(int port) {
        this.port = port;

    }

    public void startListening() {

        System.out.println("[Server] Server start...");

        new Thread(new Runnable() {
            @Override
            public void run() {

                while (true) {

                    try {
                        ServerSocket serverSocket = new ServerSocket(port);
                        System.out.println("[Server] Wait for connection...");
                        Socket remoteClientSocket = serverSocket.accept();
                        System.out.println("[Server] Client connected : " + remoteClientSocket.getRemoteSocketAddress());

                        Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(remoteClientSocket.getInputStream())));
                        if (scanner.hasNextLine()) {
                            System.out.println("[Server] Message from client: " + scanner.nextLine());
                        }

                        PrintWriter pw = new PrintWriter(new OutputStreamWriter(remoteClientSocket.getOutputStream()));
                        pw.println("Indeed it is!");
                        pw.flush();

                        // Close connections
                        scanner.close();
                        pw.close();
                        remoteClientSocket.close();
                        serverSocket.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
