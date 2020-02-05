package sockets;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDP_MultiCastSocket extends UDP_Socket {

    public UDP_MultiCastSocket(int port) throws IOException {
        super(new MulticastSocket(port));
    }

    public void join(String multiCastAddress) throws IOException {

        InetAddress castGroup = InetAddress.getByName(multiCastAddress);
        ((MulticastSocket) udpSocket).joinGroup(castGroup);
    }

    public void leave(String multiCastAddress) throws IOException {
        InetAddress castGroup = InetAddress.getByName(multiCastAddress);
        ((MulticastSocket) udpSocket).leaveGroup(castGroup);
    }


}