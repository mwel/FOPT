package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpUnicastClient implements Runnable {

    // Portnummer des Client Sockets
    private final int port;

    // Konstruktor des Clients, dem die Portnummer übergeben wird.
    public UdpUnicastClient(int port) {
        this.port = port;
    }

    // Optionale Implementation von Runnable, damit mehrere Clients parallel ausgeführt werden können.
    @Override
    public void run() {
        // try-catch Block, weil hier Exceptions geworfen werden können.
        // Datagram Socket wird im tc-Block erzeugt
        // Hier wird die Portnummer, die wir oben initialisiert haben an den clientSocket geheftet. Ohne den werden die Pakete nicht signiert und gehen verloren.
        try (DatagramSocket clientSocket = new DatagramSocket(port)) {

            // Buffer wird erstellt, der Nachrichten von bis zur angegebenen Arraygroesse speichern kann.
            byte[] buffer = new byte[1000];

            // Damit sich der Client sich nicht aufhängt, bauen wir einen Timeout von 3 Sek. ein.
            clientSocket.setSoTimeout(3000);

            // Der Client soll, wenn er eine Anfrage an den Server gesendet hat, IMMER auf die Antwort warten.
            while (true) {
                // Wir erzeugen ein Datagram Packet, das die Daten vom Server verpackt.
                DatagramPacket datagramPacket = new DatagramPacket(buffer, 0, buffer.length);
                clientSocket.receive((datagramPacket));

                // Jetzt wollen wir sehen, was in dem empfangenen Datenpacket drin ist.
                // Dafür erzeugen wir einen neuen String und übergeben ihm das Datenpacket und rufen die getData Methode auf, um die Daten aus dem Packet in den String zu schreiben.
                String receivedMessage = new String(datagramPacket.getData());
                // Dann geben wir den String einfach aus.
                System.out.println(receivedMessage);

            }


        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Timeout. Client closing.");
        }

    }


}
