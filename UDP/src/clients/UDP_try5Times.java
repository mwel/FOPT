package clients;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_try5Times {

    private static final int TIMEOUT = 10000;
    private static final int MAX_TRIES = 5;
    private static boolean receivedResponse = false;

    public static void main(String[] args) {

        if (args.length != 3) {

            System.out.println("Arguments not correct.");
            return;
        }

        int PORT = Integer.valueOf(args[1]);

        int tries = MAX_TRIES;

        while (!receivedResponse && tries > 0) {

            try (DatagramSocket datagramSocket = new DatagramSocket(PORT)) {

                datagramSocket.setSoTimeout(TIMEOUT);

                InetAddress address = InetAddress.getByName(args[0]);

                byte[] outBuffer = args[2].getBytes();
                DatagramPacket outPacket = new DatagramPacket(outBuffer, outBuffer.length, address, PORT);
                datagramSocket.send(outPacket);

                byte[] inBuffer = new byte[1000];
                DatagramPacket inPacket = new DatagramPacket(inBuffer, inBuffer.length);
                datagramSocket.receive(inPacket);
                InetAddress receiverAddress = inPacket.getAddress();

                if (receiverAddress.equals(address)) {
                    receivedResponse = true;
                    break;
                } else {
                    tries--;
                }

            } catch (Exception e) {
                System.out.println("Tried " + MAX_TRIES + " times. Giving up.");
                e.printStackTrace();
            }
        }

    }
}
