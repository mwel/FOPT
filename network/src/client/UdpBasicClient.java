package client;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class UdpBasicClient {

/* Kochrezept für UDP Client

0. Main entry point
1. TC Block
2. Erstelle neuen Datagram Socket
3. Erstelle Buffer - gleich wie beim Server
4. while (true)
5. Erstelle neuen String, in dem die Anfrage and den server gespeichert werden kann.
6. Erstelle Datagram Packet und übergebe die Serveranfrage darin als bytes, die byte-Array-Länge und die eigene Adresse sowie die Portnummer des Servers.
7. Schicke das Packet.
8. Erstelle ein neues Datagrampacket für die Antwort vom Server und übergebe den Puffer und dessen Länge.
9. rufe per receive über den client Socket das nächste Paket vom Server auf.
10. Drucke die Daten aus dem Echo-Paket vom Server in ein neues String-Objekt.

 */

    public static void main(String[] args) {

        try {

            Scanner scanner = new Scanner(System.in);

            DatagramSocket clientSocket = new DatagramSocket();

            byte[] buffer = new byte[1024];

            while (true) {

                System.out.print("> ");

                String data = scanner.nextLine();
                if ("exit".equalsIgnoreCase(data))
                    break;

                DatagramPacket packageToServer = new DatagramPacket(data.getBytes(), data.getBytes().length, InetAddress.getLocalHost(), 54824);
                clientSocket.send(packageToServer);

                DatagramPacket echoPackage = new DatagramPacket(buffer, buffer.length);
                clientSocket.receive(echoPackage);

                System.out.println("[SERVER-ECHO] : " + new String(echoPackage.getData()));

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
