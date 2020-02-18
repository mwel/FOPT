package udpTemplates;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPStaticParallelServer
{
    private static final int WORKER_COUNT = 100;

    public static void main(String[] args)
    {
        try (DatagramSocket socket = new DatagramSocket(1250))
        {
            System.out.println("Server is waiting...");

            Thread threads[] = new Thread[WORKER_COUNT];

            for (int i = 0; i < WORKER_COUNT; i++)
            {
                Thread thread = new Thread(() -> execute(socket));
                thread.start();
                threads[i] = thread;
            }

            for (Thread thread : threads)
            {
                thread.join();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        System.out.println("Socket wurde geschlossen");
    }

    private static void execute(DatagramSocket socket)
    {
        try
        {
            while (true)
            {
                byte[] inBuf = new byte[1024];
                DatagramPacket inPacket = new DatagramPacket(inBuf, inBuf.length);
                socket.receive(inPacket);

                InetAddress clientAddr = inPacket.getAddress();
                int clientPort = inPacket.getPort();

                String inMsg = new String(inPacket.getData(), 0, inPacket.getLength());
                System.out.println("Server empfing (" + clientPort + "): " + inMsg);

                Thread.sleep(2000);

                String outMsg = inMsg + " - OK - " + Thread.currentThread().getName();
                byte[] outBuf = outMsg.getBytes();
                DatagramPacket outPacket = new DatagramPacket(outBuf, outBuf.length, clientAddr, clientPort);
                socket.send(outPacket);
                System.out.println("Server sendete (" + clientPort + "): " + outMsg);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
