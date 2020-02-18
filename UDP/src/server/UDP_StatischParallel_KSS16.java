package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDP_StatischParallel_KSS16 {

         /*Programmieren sie eine Statisch Parallelen udp Server.
        Gehen sie davon aus, dass Byte Werte (kein ascii Protokoll) mit einer maximalen Länge von 1000 Bytes empfangen werden.
        Dieses Byte Array übergeben sie mit seiner Länge an die Methode Process welche ein byte array als Antwort generiert.
        Dieses Array muss komplett zurückgesendet werden.
        Erstellen sie die Threads selber und verwenden kein Thread Pool. Die Anzahl der Threads ist ihnen überlassen.*/

    private static final int WORKER_COUNT = 5;
    private static final int PORT = 1250;

    public static void main(String[] args) throws SocketException {

        DatagramSocket datagramSocket = new DatagramSocket(PORT);

        for (int i = 0; i < WORKER_COUNT; i++) {
            Thread worker = new Worker(datagramSocket);
            worker.start();
        }
    }
}

class Worker extends Thread {

    private DatagramSocket socket;
    UDP_StatischParallel_KSS16 server;
    byte[] inBuffer;
    byte[] outBuffer;


    public Worker(DatagramSocket datagramSocket) {
        this.socket = datagramSocket;
    }

    @Override
    public void run() {

        try (DatagramSocket socket = this.socket) {

            // receive
            inBuffer = new byte[1000];
            DatagramPacket inPacket = new DatagramPacket(inBuffer, inBuffer.length);
            socket.receive(inPacket);
            InetAddress address = inPacket.getAddress();
            int port = inPacket.getPort();

            // respond
            outBuffer = process(inBuffer, inPacket.getLength());
            DatagramPacket outPacket = new DatagramPacket(outBuffer, outBuffer.length, address, port);
            socket.send(outPacket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public byte[] process(byte[] byteArray, int length) {

        // byteArray wird manipuliert.

        return byteArray;
    }


}