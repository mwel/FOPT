package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPDynParServer {

    private static final int PORT = 9274;

    public static void main(String[] args) {

        try {

            DatagramSocket socket = new DatagramSocket(PORT);

            System.out.println("Server " + PORT + " running and waiting . . . ");

            while (true) {

                byte[] inBuffer = new byte[1024];
                DatagramPacket inPacket = new DatagramPacket(inBuffer, inBuffer.length);
                socket.receive(inPacket);

                UDP_DynParWorker worker = new UDP_DynParWorker(socket, inPacket);
                worker.start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class UDP_DynParWorker extends Thread {

    private DatagramSocket socket;
    private DatagramPacket inPacket;

    public UDP_DynParWorker(DatagramSocket socket, DatagramPacket inPacket) {

        this.socket = socket;
        this.inPacket = inPacket;
    }

    public void run() {

        try {

            String inMessage = new String(inPacket.getData(), 0, inPacket.getLength());

            InetAddress clientAddress = inPacket.getAddress();
            int clientPort = inPacket.getPort();

            Thread.sleep(10000);

            String outMessage = "Server response: Message (" + inMessage + ") from client [" + clientAddress + ":" + clientPort + " received.";
            byte[] outBuffer = outMessage.getBytes();
            DatagramPacket outPacket = new DatagramPacket(outBuffer, outBuffer.length, clientAddress, clientPort);
            socket.send(outPacket);

            System.out.println("Client [" + clientAddress + ":" + clientPort + " handeled.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}