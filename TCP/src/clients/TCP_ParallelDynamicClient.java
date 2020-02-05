package clients;

import sockets.TCP_Socket;

import java.io.IOException;

public class TCP_ParallelDynamicClient {

    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Required arguments: <server name>, <increment / reset>");
            return;
        }

        // create socket connection
        System.out.println("Establishing connection.");
        try (TCP_Socket tcpSocket = new TCP_Socket(args[0], 1250)) {

            //peform sleep operations
            for (int i = 1; i < args.length; i++) {
                tcpSocket.sendLine(args[i]);
                String result = tcpSocket.receiveLine();
                System.out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("TCP connection closed.");

    }
}
