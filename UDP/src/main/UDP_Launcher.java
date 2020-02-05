package main;

import clients.UDP_CounterClient;

public class UDP_Launcher {

    public static void main(String[] args) {

        UDP_CounterClient counterClient = new UDP_CounterClient();
        counterClient.main(new String[]{"localhost", "1000"});


    }
}
