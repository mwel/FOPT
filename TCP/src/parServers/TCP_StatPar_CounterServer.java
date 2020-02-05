package parServers;

import sockets.TCP_Socket;

import java.io.IOException;
import java.net.ServerSocket;


class Counter {

    private int counter;

    public synchronized int increment() {
        counter++;
        return counter;
    }

    public synchronized int reset() {
        counter = 0;
        return counter;
    }

    public synchronized int getCount() {
        return counter;
    }
}


public class TCP_StatPar_CounterServer { // Statisch paralleler Counter Server aus dem Buch

    public static final int DEFAULT_PORT = 1250;
    private static final int WORKER_COUNT = 20; // Statische Anzahl Threads, die gleichzeitig Verbindungn annehmen können

    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        Counter counter = new Counter();
        try {
            //create socket
            serverSocket = new ServerSocket(DEFAULT_PORT);

        } catch (IOException e) {
            System.out.println("Failed to create server socket.");
            return;
        }

        for (int i = 0; i < TCP_StatPar_CounterServer.WORKER_COUNT; i++) {
            Thread worker = new StaticWorker(serverSocket, counter);
            worker.start();
        }

    }

}

class StaticWorker extends Thread {

    private ServerSocket serverSocket;
    private Counter counter;

    public StaticWorker(ServerSocket serverSocket, Counter counter) {

        this.serverSocket = serverSocket;
        this.counter = counter;
    }

    public void run() {
        while (true) {
            //wait for connection then create streams
            System.out.println("Server running and waiting for connection . . .");

            try (TCP_Socket tcpSocket = new TCP_Socket(serverSocket.accept())) {

                while (true) {
                    String request = tcpSocket.receiveLine();

                    //execute client requests
                    int result;
                    if (request != null) {

                        if (request.equals("increment")) {
                            // perform increment operation
                            result = counter.increment();

                        } else if (request.equals("reset")) {
                            //perform reset
                            result = counter.reset();
                            System.out.println("Counter reset.");
                        } else {
                            result = counter.getCount();
                        }
                        tcpSocket.sendLine("" + result);

                    } else {
                        System.out.println("Closing TCP connection.");
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("TCP connection closed.");
            }
        }
    }
}