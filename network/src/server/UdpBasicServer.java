package server;

import java.io.IOException;
import java.net.*;

public class UdpBasicServer {

/* Kochrezept für UDP Server

0. Main entry point
1. try catch block
2. Erstelle im try catch block einen DatagramSocket für den Server.
3. Übergebe ihm eine beliebige Portnummer und die localhost-Addresse.
4. Baue einen byte-Puffer mit einer sinnvollen Arraylänge
5. Erzeuge zwei DataPackets. Eines für Empfang und eines für Senden. Übergebe dabei den Buffer und dessen Länge.
6. while(true)
7. Rufe receive aus dem serverSocket auf und übergebe das zu empfangene Paket.
8. Schreibe die Daten in einen String.
9. Printe den String.
10. Hole Adresse, Port und Daten und schreibe sie als Anschrift für die Antwort in das zu sendende Paket.
11. schicke das Paket ab.

 */

    public static void main(String[] args) {

        try {

            DatagramSocket serverSocket = new DatagramSocket(54824, InetAddress.getLocalHost());

            byte[] buffer = new byte[1024];

            DatagramPacket packetSend = new DatagramPacket(buffer, buffer.length);
            DatagramPacket packetReceive = new DatagramPacket(buffer, buffer.length);

            while (true) {

                serverSocket.receive(packetReceive);

                String message = new String(packetReceive.getData());

                System.out.printf("Package received from %s:%d, content:  %s\n", packetReceive.getAddress().getHostAddress(), packetReceive.getPort(), message);

                packetSend.setAddress(packetReceive.getAddress());
                packetSend.setPort(packetReceive.getPort());
                packetSend.setData(packetReceive.getData());

                serverSocket.send(packetSend);
            }


        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
