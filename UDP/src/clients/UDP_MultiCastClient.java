package clients;

import sockets.UDP_Socket;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDP_MultiCastClient {

    private static final int TIMEOUT = 2000; // 2 second timeout, if server does not answer.

    public static void main(String[] args) {

        if (args.length < 2) {

            System.out.println("Required arguments: <Multi-Cast IP-Address> <Message 1> . . . <Message n>");
            return;
        }

        //create datagram socket
        try (UDP_Socket udp_socket = new UDP_Socket()) {

            udp_socket.setTimeout(TIMEOUT);

            //get inet address of server
            InetAddress serverAddress = InetAddress.getByName(args[0]);

            for (int i = 0; i < args.length; i++) {
                udp_socket.send(args[i], serverAddress, 1250);

                try {
                    while (true) {
                        String reply = udp_socket.receive(200);
                        System.out.println("Message received: " + udp_socket.getSenderAddress() + ":" + udp_socket.getSenderPort() + ":" + reply);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Multi-Cast-Socket closed");

    }
}
