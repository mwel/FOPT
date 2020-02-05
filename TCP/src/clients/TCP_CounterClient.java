package clients;

import seqServers.TCP_CounterServer;
import sockets.TCP_Socket;

import java.io.IOException;

public class TCP_CounterClient {

    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.out.println("Required arguments: <server name>, <amount>");
            return;
        }

        // create socket connection
        System.out.println("Establishing connection.");
        try (TCP_Socket tcpSocket = new TCP_Socket(args[0], TCP_CounterServer.DEFAULT_PORT)) {

            //Reset counter
            System.out.println("Reset counter.");
            tcpSocket.sendLine("reset");

            System.out.println("Answer: " + tcpSocket.receiveLine());

            // get count
            System.out.println("Increasing counter.");
            final int incrementCount = Integer.parseInt(args[1]);

            // Save start time to calculate stats later
            long startTime = System.currentTimeMillis();

            //perform incrementCount number of times
            for (int i = 0; i < incrementCount; i++) {
                tcpSocket.sendLine("increment");
                System.out.println("Answer: " + tcpSocket.receiveLine());
            }

            //display stats - OPTIONAL!
            long stopTime = System.currentTimeMillis();
            long duration = stopTime - startTime;
            System.out.println("Total operation time = " + duration + " msecs");

            if (incrementCount > 0) {
                System.out.println("Average time = " + ((duration) / (float) incrementCount) + " secs");
            }
            System.out.println("Last count: " + incrementCount);

            System.out.println("TCP-Socket closed");
        }
    }
}