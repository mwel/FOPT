package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPStatParServer {

    private static final int WORKER_COUNT = 100;
    private static final int DEFAULT_PORT = 1274;

    public static void main(String[] args) {

        try {

            DatagramSocket socket = new DatagramSocket(DEFAULT_PORT);
            System.out.println("Server " + DEFAULT_PORT + " running and waiting . . . ");

            for (int i = 0; i < WORKER_COUNT; i++) {
                UDP_StatParWorker worker = new UDP_StatParWorker(socket);
                worker.start();
            }

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}

class UDP_StatParWorker extends Thread {

    private DatagramSocket socket;

    public UDP_StatParWorker(DatagramSocket socket) {

        this.socket = socket;
    }

    public void run() {

        try {

            while (true) {

                byte[] inBuffer = new byte[1024];
                DatagramPacket inPacket = new DatagramPacket(inBuffer, inBuffer.length);
                socket.receive(inPacket);

                InetAddress clientAddress = inPacket.getAddress();
                int clientPort = inPacket.getPort();

                String inMessage = new String(inPacket.getData(), 0, inPacket.getLength());

                System.out.println("Client [" + clientAddress + ":" + clientPort + "] requested: " + inMessage);

                Thread.sleep(10000);

                String outMessage = "Server response: Message (" + inMessage + ") from client [" + clientAddress + ":" + clientPort + " received.";
                byte[] outBuffer = outMessage.getBytes();
                DatagramPacket outPacket = new DatagramPacket(outBuffer, outBuffer.length, clientAddress, clientPort);
                socket.send(outPacket);

                System.out.println("Client [" + clientAddress + ":" + clientPort + " handeled.");

            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
}
