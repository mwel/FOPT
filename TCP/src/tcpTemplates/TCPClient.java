package tcpTemplates;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class TCPClient
{
    public static void main(String args[])
    {
        try (Socket socket = new Socket("localhost", 1250))
        {
            String outMsg = "Hello World";
            BufferedWriter outStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            outStream.write(outMsg);
            outStream.newLine();
            outStream.flush();
            System.out.println("Client sendete: " + outMsg);            
            
            BufferedReader inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inMsg = inStream.readLine();
            System.out.println("Client empfing: " + inMsg);             
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        System.out.println("TCP-Verbindung wurde geschlossen");
    }
}
