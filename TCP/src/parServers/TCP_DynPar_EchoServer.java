package parServers;

import sockets.TCP_Socket;

import java.net.ServerSocket;

public class TCP_DynPar_EchoServer { // Dynamisch Paralleler Server aus dem Buch

    public static void main(String[] args) {

        //create socket
        try (ServerSocket serverSocket = new ServerSocket(1250)) {

            while (true) {

                // wait for connection then create streams
                System.out.println("Server running and waiting for connection . . .");
                try {
                    TCP_Socket tcpSocket = new TCP_Socket(serverSocket.accept());
                    new Worker(tcpSocket);

                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error while creating or using server socket.");
        }
    }
}

class Worker extends Thread {

    private TCP_Socket tcpSocket;

    public Worker(TCP_Socket tcpSocket) {
        this.tcpSocket = tcpSocket;
        this.start();
    }

    public void run() {
        try (TCP_Socket workerTcpSocket = tcpSocket) {
            while (true) {
                String request = workerTcpSocket.receiveLine();
                //execute client requests
                if (request != null) {
                    try {
                        int secs = Integer.parseInt(request);
                        Thread.sleep(secs * 1000);
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    workerTcpSocket.sendLine(request);
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("TCP connection closed.");
    }
}
