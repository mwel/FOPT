package parServers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_DynPar_CounterServer {

    public static final int DEFAULT_PORT = 1250;

    public static void main(String[] args) throws IOException {

        final SynchronizedCounter synchronizedCounter = new SynchronizedCounter();

        try (final ServerSocket serverSocket = new ServerSocket(TCP_DynPar_CounterServer.DEFAULT_PORT)) {

            while (true) {

                final Socket socket = serverSocket.accept(); // Hier wird eine Verbindung angenommen, aber nicht mehr geschlossen.

                final TCP_CounterServerRunnable runnable = new TCP_CounterServerRunnable(socket, synchronizedCounter); // Das Runnable kümmert sich um das Schließen.

                final Thread workerThread = new Thread(runnable);

                workerThread.start();

            }
        }

    }

}
