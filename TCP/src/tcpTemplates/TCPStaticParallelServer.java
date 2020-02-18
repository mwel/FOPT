package tcpTemplates;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPStaticParallelServer
{
    public static void main(String[] args)
    {
        try (ServerSocket serverSocket = new ServerSocket(1250))
        {
            System.out.println("Server is waiting...");

            int count = 2;
            Thread threads[] = new Thread[count];

            for (int i = 0; i < count; i++)
            {
                Thread thread = new Thread(() -> execute(serverSocket));
                thread.start();
                threads[i] = thread;
            }

            for (Thread thread : threads)
            {
                thread.join();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        System.out.println("Socket wurde geschlossen");
    }

    private static void execute(ServerSocket serverSocket)
    {
        try
        {
            while (true)
            {
                Socket socket = serverSocket.accept();
                int clientPort = socket.getPort();

                BufferedReader inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String inMsg = inStream.readLine();
                System.out.println("Server empfing (" + clientPort + "): " + inMsg);

                Thread.sleep(2000);

                String outMsg = inMsg + " - OK - " + Thread.currentThread().getName();
                BufferedWriter outStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                outStream.write(outMsg);
                outStream.newLine();
                outStream.flush();
                System.out.println("Server sendete (" + clientPort + "): " + outMsg);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
