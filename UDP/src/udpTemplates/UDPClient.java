package udpTemplates;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient
{
    public static void main(String args[])
    {
        try (DatagramSocket socket = new DatagramSocket())
        {
            socket.setSoTimeout(5000);
            InetAddress serverAddr = InetAddress.getByName("localhost");

            String outMsg = "Hello World";
            byte[] outBuf = outMsg.getBytes();
            DatagramPacket outPacket = new DatagramPacket(outBuf, outBuf.length, serverAddr, 1250);
            socket.send(outPacket);
            System.out.println("Client sendete: " + outMsg);

            byte[] inBuf = new byte[1024];
            DatagramPacket inPacket = new DatagramPacket(inBuf, inBuf.length);
            socket.receive(inPacket);
            String inMsg = new String(inPacket.getData(), 0, inPacket.getLength());
            System.out.println("Client empfing: " + inMsg);
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        System.out.println("DatagramSocket wurde geschlossen");
    }
}
