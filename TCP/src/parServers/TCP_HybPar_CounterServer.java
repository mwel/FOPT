package parServers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TCP_HybPar_CounterServer {

    public static final int DEFAULT_PORT = 1250;
    public static final int MIN_WORKER = 3;
    public static final int MAX_WORKER = 1000;

    public static void main(String[] args) throws IOException {

        final SynchronizedCounter counter = new SynchronizedCounter();

        final ThreadPoolExecutor executionService = new ThreadPoolExecutor(TCP_HybPar_CounterServer.MIN_WORKER, TCP_HybPar_CounterServer.MAX_WORKER, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>());
        // Die komplette Logik ist in dem TPE drin - muss man sich nicht drum kümmern.

        try (final ServerSocket serverSocket = new ServerSocket(TCP_HybPar_CounterServer.DEFAULT_PORT)) {

            while (true) {

                final Socket socket = serverSocket.accept(); // Hier wird eine Verbindung angenommen, aber nicht mehr geschlossen.

                final TCP_CounterServerRunnable runnable = new TCP_CounterServerRunnable(socket, counter); // Das Runnable kümmert sich um das Schließen.
                executionService.execute(runnable);

            }
        }
    }
}
