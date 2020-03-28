package sockets;

import com.sun.corba.se.impl.orbutil.ObjectWriter;
import sun.jvm.hotspot.utilities.ObjectReader;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class TCP_Socket implements AutoCloseable {

    private static final Charset ENC_CHARSET = StandardCharsets.UTF_8;

    private Socket socket; // Originalsocket, über den die Kommunikation stattfindet wird hier gekapselt.
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;

    private ObjectReader objectReader;
    private ObjectWriter objectWriter;

    public TCP_Socket(String serverAddress, int serverPort) throws IOException { // Konstruktor für den Client mit IP-Adresse und Portnummer
        // Adresse und Portnummer des Kommunikationspartners kommen an. Wir erzeugen ein neues Original-Socket-Objekt.
        this(new Socket(serverAddress, serverPort)); // Das Original-Socket-Onjekt verketten wir hier dann mit dem Server-Konstruktor
    }

    public TCP_Socket(final Socket socket) throws IOException { // Konstruktor für den Server mit einem Socket-Objekt

        this.socket = socket; // Hier kommt das Socket-Objekt an, das wir über serverSocket.accept() angenommen haben.
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), ENC_CHARSET));
        this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), ENC_CHARSET));
        // alle drei Elemente speichern wir hier in die lokalen Variablen weg, damit gearbeitet werden kann.
    }

    public void sendLine(final String line) throws IOException { // Zeichenkette wird versendet.

        bufferedWriter.write(line);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    public String receiveLine() throws IOException { // Zeichenkette wird empfangen.

        return this.bufferedReader.readLine();
    }

    public void close() throws IOException {

        this.socket.close();
    }

    public void sentInt (int i) throws IOException {

        this.bufferedWriter.write(i);
    }

    public Object receiveObject () {

        return objectReader.readObject();
    }
}
