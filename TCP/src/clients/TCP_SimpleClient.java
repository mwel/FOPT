package clients;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class TCP_SimpleClient { // TCP Client aus der Vorlesungsaufzeichnung

    public static void main(final String[] args) throws IOException {

        final InetAddress serverAddress = InetAddress.getByName(args[0]); // IP-Adresse oder DNS-Name

        try /* with resources */ (final Socket socket = new Socket(serverAddress, 1250)) { // Bei Nichterreichbarkeit wird IOE geworfen.

            //SCHREIBEN & SENDEN
            final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));

            writer.write("Hallo, hier der Client."); //Hier schreiben wir eine Nachricht über den OSW in den BW

            writer.newLine();   /*Zeilenumbruch wird als Trennzeichen von Befehlen verwendet. So kann der Server die
            verschiedenen Befehle auseinanderhalten. Ohne das newLine() würde bei der Ankunft
            am Server alles aus dem Buffer in ein zusammenhängendes Paket geschrieben und
            die Nachricht wäre unleserlich.*/

            writer.flush();     /* Weil der BW evtl. nicht voll geworden ist, was zu einem automatischen flush() geführt
             hätte, flushen wir hier manuell. */

            //EMPFANGEN & LESEN
            final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
            final String answer = reader.readLine();

            System.out.println(answer);

        } // Hier wird der Socket über das TWR (try with resources) geschlossen. Manuell muss also nicht mehr geschlossen werden.
    }
}
