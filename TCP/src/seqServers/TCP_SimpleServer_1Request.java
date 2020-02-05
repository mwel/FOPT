package seqServers;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCP_SimpleServer_1Request { // Server für nur eine Client Request.

    public static void main(String[] args) throws IOException {

        try (final ServerSocket serverSocket = new ServerSocket(1250)) { /* Wenn der Server hochfährt und es noch
        keine Clients gibt, dann kann es auch noch keine Verbindung geben. Weil TCP aber verbindungsbasiert arbeitet,
        muss eine Art Entry-Point für den Aufbau einer Verbindung geschaffen werden. Das ist der ServerSocket.*/

            while (true) {

                try (final Socket socketToClient = serverSocket.accept()) { /* Wir rufen auf dem ServerSocket Objekt
                die Methode accept() auf. Darin warten wir so lange, bis ein Client auf unser Angebot antwortet und
                kommen erst dann aus der Methode zurück. Die Rückgabe dieser Methode ist
                ein Socket-Objekt = unser Ende der Verbindung.*/

                    TCP_SimpleServer_1Request.processConnection(socketToClient); // Request-Handling wird ausgelagert.
                } // Automatischer Verbindungsabbau und Schließen des Ströme des Sockets. Wenn ein Socket geschlossen wird, dann auch die Ströme, die daran hängen und wenn ein Strom geschlossen wird, dann wird automatisch nochmal ein flush Aufgruf gemacht.
            }
        }
    }

    private static void processConnection(final Socket socket) throws IOException {

        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        final String request = bufferedReader.readLine(); // Die readLine() blockiert so lange, bis zeichenweise die ganze Request vom Client gelesen wurde und das newline Symbol gelesen wird. Hier kommt jetzt die newLine() aus dem Client zum tragen..

        final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        bufferedWriter.write("Echo: " + request);
        bufferedWriter.newLine();
        bufferedWriter.flush(); // Braucht man hier trotzdem, weil der automatische flush, der durch TWR in der main() ausgeführt wird, nicht für den lokalen BW hier gilt.
    }
}
