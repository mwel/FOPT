package seqServers;

import sockets.TCP_Socket;

import java.net.ServerSocket;

public class TCP_EchoServer { // normal sequential server with not parallel implementation

    public static void main(String[] args) {

        // create socket
        try (ServerSocket serverSocket = new ServerSocket(1250)) {

            while (true) {

                // wait for connection then create streams
                System.out.println("Server running and waiting for connection . . .");
                try (TCP_Socket tcpSocket = new TCP_Socket(serverSocket.accept())) {

                    // execute client requests
                    while (true) {
                        String request = tcpSocket.receiveLine();

                        if (request != null) {

                            // perform sleep
                            try {
                                int secs = Integer.parseInt(request);
                                Thread.sleep(secs * 1000);
                            } catch (Exception e) {
                                System.out.println(e);
                            }
                            tcpSocket.sendLine(request);

                        } else {
                            System.out.println("Closing TCP connection.");
                            break;
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
                System.out.println("TCP connection closed.");
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error while creating or using server socket.");
        }
    }
}