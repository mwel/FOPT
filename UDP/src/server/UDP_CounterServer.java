package server;

import sockets.UDP_Socket;

public class UDP_CounterServer {

    public static void main(String[] args) {

        int counter = 0;

        //create Socket
        try (UDP_Socket udpSocket = new UDP_Socket(1250)) {

            //wait for request packets
            System.out.println("Server waiting for clients . . .");

            //execute client request
            while (true) {

                // receive request
                String request = udpSocket.receive(20);

                // perform increment operation
                if (request.equals("increment")) {
                    //perform increment
                    counter++;
                } else if (request.equals("reset")) {
                    //perform reset
                    counter = 0;
                    System.out.println("Set counter to 0 by " + udpSocket.getSenderAddress() + ":" + udpSocket.getSenderPort());
                }

                //generate answer
                String answer = String.valueOf(counter);

                //send answer
                udpSocket.reply(answer);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("UDP-Socket closed.");
    }
}
