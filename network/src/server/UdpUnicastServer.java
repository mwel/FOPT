package server;

import java.io.IOException;
import java.net.*;

public class UdpUnicastServer implements Runnable {

    // Der Server muss den Client Port kennen - sonst weiß er nicht, wohin er die Antwort schicken soll.
    private final int clientPort;

    // Konstruktor - hier wird der client Port übergeben.
    public UdpUnicastServer(int clientPort) {
        this.clientPort = clientPort;
    }

    @Override
    public void run() {

        // Wir erzeugen einen neuen ServerSocket im TC-Block und übergeben ihm die Portnummer
        try (DatagramSocket serverSocket = new DatagramSocket(50000)) { // irgenein Port, den keine andere App benutzt.

            // Antwort vom Server:
            String message = "[SERVER] : Here is your answer.";

            // Jetzt verpacken wir jede Nachricht in ein Datagram Packet. In das Packet legen wir die eigentliche Message, aver als byte-Array codiert, die Länge dieses Arrays, damit der Empfänger weiss, wann er aufhören kann zu lesen. die Absenderadresse und die Empfängeradresse auf die der Client hört.
            DatagramPacket datagramPacket = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getLocalHost(), clientPort);

            // Jetzt müssen wir die Antworten noch senden. Dafür rufen wir die send-Methode des Sockets auf und übergeben das Packet, das wir senden wollen. Der Socket erledigt den Versand.
            serverSocket.send(datagramPacket);


        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
