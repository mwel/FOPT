package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class UDPHybParServer {

    private static final int MIN_WORKER_COUNT = 3;
    private static final int MAX_WORKER_COUNT = 20;
    private static final int PORT = 9876;

    public static void main(String[] args) {

        ThreadPoolExecutor workerPool =
                new ThreadPoolExecutor(MIN_WORKER_COUNT, MAX_WORKER_COUNT,
                        1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(50));

        System.out.println("ThreadPool created. Server waiting . . .");

        try {

            DatagramSocket socket = new DatagramSocket(PORT);

            while (true) {

                byte[] inBuffer = new byte[1024];
                DatagramPacket inPacket = new DatagramPacket(inBuffer, inBuffer.length);
                socket.receive(inPacket);

                UDP_HybridWorker worker = new UDP_HybridWorker(socket, inPacket);
                workerPool.execute(worker);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class UDP_HybridWorker extends Thread {

    private DatagramSocket socket;
    private DatagramPacket inPacket;

    public UDP_HybridWorker(DatagramSocket socket, DatagramPacket inPacket) {

        this.socket = socket;
        this.inPacket = inPacket;
    }

    public void run() {

        try {

            while (true) {

                InetAddress clientAddress = inPacket.getAddress();
                int clientPort = inPacket.getPort();

                String inMessage = new String(inPacket.getData(), 0, inPacket.getLength());

                System.out.println("Client [" + clientAddress + ":" + clientPort + "] requested: " + inMessage);

                Thread.sleep(2000);

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
