package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDP_Client {

    private static final int PORT = 9876;

    private static int packetAmount = 2000;
    private static int packetsSentCounter = 0;
    private static int packetsReceivedCounter = 0;

    public static void main(String[] args) {

        try (DatagramSocket clientSocket = new DatagramSocket()) {

            for (int i = 0; i < packetAmount; i++) {
                InetAddress serverAddress = InetAddress.getByName("localhost");
                String outMessage = "UDP_ServerRequest";
                byte[] outBuffer = outMessage.getBytes();
                DatagramPacket outPacket = new DatagramPacket(outBuffer, outBuffer.length, serverAddress, PORT);
                clientSocket.send(outPacket);
                packetsSentCounter++;
                System.out.println("Packets sent [" + packetsSentCounter + "]");

            }

            clientSocket.setSoTimeout(20000);

            for (int i = 0; i < packetAmount; i++) {
                byte[] inBuffer = new byte[1024];
                DatagramPacket inPacket = new DatagramPacket(inBuffer, inBuffer.length);
                clientSocket.receive(inPacket);
                String inMessage = new String(inPacket.getData(), 0, inPacket.getLength());
                packetsReceivedCounter++;
                System.out.println("Packets received [" + packetsReceivedCounter + "]");
                System.out.println("Server " + inPacket.getAddress() + ":" + inPacket.getPort() + " | " + inMessage);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
