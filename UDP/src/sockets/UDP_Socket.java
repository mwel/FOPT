package sockets;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDP_Socket implements AutoCloseable {

    protected DatagramSocket udpSocket;
    private InetAddress address;
    private int port;

    public UDP_Socket() throws SocketException {

        this(new DatagramSocket());
    }

    public UDP_Socket(int port) throws SocketException {

        this(new DatagramSocket(port));
    }

    public UDP_Socket(DatagramSocket udpSocket) {

        this.udpSocket = udpSocket;
    }

    public void send(String string, InetAddress receiverAddress, int receiverPort) throws IOException {

        byte[] outBuffer = string.getBytes();
        DatagramPacket outPacket = new DatagramPacket(outBuffer, outBuffer.length, receiverAddress, receiverPort);
        udpSocket.send(outPacket);
    }

    public String receive(int maxBytes) throws IOException {

        byte[] inBuffer = new byte[maxBytes];
        DatagramPacket inPacket = new DatagramPacket(inBuffer, inBuffer.length);
        udpSocket.receive(inPacket);
        address = inPacket.getAddress();
        port = inPacket.getPort();

        return new String(inBuffer, 0, inPacket.getLength());
    }

    public void reply(String string) throws IOException {

        if (address == null) {
            throw new IOException("No one to reply.");
        }
        send(string, address, port);
    }

    public InetAddress getSenderAddress() {
        return address;
    }

    public int getSenderPort() {
        return port;
    }

    public void setTimeout(int timeout) throws SocketException {

        udpSocket.setSoTimeout(timeout);
    }

    @Override
    public void close() {

        udpSocket.close();
    }
}
