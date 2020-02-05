package server;

import sockets.UDP_MultiCastSocket;

public class UDP_MultiCastServer {

    public static void main(String[] args) {

        if (args.length != 1) {

            System.out.println("Required argument: <multi cast IP address>");
            return;
        }

        try (UDP_MultiCastSocket multiCastSocket = new UDP_MultiCastSocket(1250)) {

            System.out.println("Multi-Cast-Socket created.");
            multiCastSocket.join(args[0]);
            System.out.println("Joined Multi-Cast-Group.");

            while (true) {

                String request = multiCastSocket.receive(200);
                System.out.println("Message received: " + multiCastSocket.getSenderAddress() + ":" + multiCastSocket.getSenderPort() + ":" + request);
                multiCastSocket.reply(request);
                if (request.equals("exit")) {
                    break;
                }
            }
            multiCastSocket.leave(args[0]);
            System.out.println("Left Multi-Cast-Group.");


        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Multi-Cast-Socket closed.");

    }
}
