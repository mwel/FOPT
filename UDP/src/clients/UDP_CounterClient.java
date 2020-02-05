package clients;

import sockets.UDP_Socket;

import java.net.InetAddress;

public class UDP_CounterClient {

    private static final int TIMEOUT = 10000; // 10 second timeout, if server does not answer.

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Required arguments: <server name> <amount>");
            return;
        }

        //create datagram socket
        try (UDP_Socket udpSocket = new UDP_Socket()) {

            udpSocket.setTimeout(TIMEOUT);

            // get inet address of server
            InetAddress serverAddress = InetAddress.getByName(args[0]);

            //set counter to 0
            System.out.println("Setting counter to 0.");
            udpSocket.send("reset", serverAddress, 1250);

            String reply = null;

            //receive reply
            try {
                reply = udpSocket.receive(20);
                System.out.println("Counter at " + reply);
            } catch (Exception e) {
                System.out.println(e);
            }

            // get count, intialize start time
            System.out.println("Now, counter will start to increment.");
            int count = new Integer(args[1]).intValue();
            long startTime = System.currentTimeMillis();

            //perform increment "count" number of times
            for (int i = 0; i < count; i++) {
                udpSocket.send("increment", serverAddress, 1250);
                try {
                    reply = udpSocket.receive(20);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

            //display statistics
            long stopTime = System.currentTimeMillis();
            long duration = stopTime - startTime;
            System.out.println("Total operation time = " + duration + " msecs");

            if (count > 0) {
                System.out.println("Average time = " + ((duration) / (float) count) + " msecs");
            }
            System.out.println("Last count: " + reply);


        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("UDP-Socket closed");
    }
}
