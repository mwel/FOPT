import client.UdpUnicastClient;
import server.UdpUnicastServer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UdpUnicastMain {

    public static void main(String[] args) {

        // Wir brauchen einen gemeinsamen Port mit dem wir senden UND empfangen.
        int port = 48564;
        UdpUnicastServer server = new UdpUnicastServer(port);
        UdpUnicastClient client = new UdpUnicastClient(port);

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(client);
        executorService.submit(server);


    }
}
