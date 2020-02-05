package parServers;

import sockets.TCP_Socket;

import java.io.IOException;
import java.net.Socket;

public class TCP_CounterServerRunnable implements Runnable { // Das ist das Runnable Objekt für den dynamisch parallelen CounterServer "TCP_DynPar_CounterServer"

    private final Socket socket;
    private final SynchronizedCounter counter;

    public TCP_CounterServerRunnable(Socket socket, SynchronizedCounter counter) {
        this.socket = socket;
        this.counter = counter;
    }

    @Override
    public void run() {

        try (final TCP_Socket tcpSocket = new TCP_Socket(this.socket)) {

            String request = null;
            while ((request = tcpSocket.receiveLine()) != null) {

                final int answer = this.processRequestAndReturnAnswer(request);
                this.delay(1000);
                tcpSocket.sendLine(String.valueOf(answer));

            }

        } catch (final IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private int processRequestAndReturnAnswer(final String request) {
        // Behandlung der übergebnen Anfrage auf den übergebenen Zähler, Rückgabe der Antwort.

        if (request.equals("increment")) {
            return this.counter.increment();

        } else if (request.equals("reset")) {
            return this.counter.reset();
        }

        throw new RuntimeException("Invalid request " + request);
    }

    private void delay(final long millisToDelay) {
        // sleep Methode mit der wir simulieren, dass der Server ausgelastet waere.
        try {
            Thread.sleep(millisToDelay);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }

    }


}
