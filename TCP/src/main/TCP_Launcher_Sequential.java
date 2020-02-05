package main;

import clients.TCP_CounterClient;

import java.io.IOException;

public class TCP_Launcher_Sequential {

    public static final String LOCAL_HOST = "localhost";
    public static final int AMOUNT = (int) (Math.random() * 10);


    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 50; i++) {
            final Thread client = new Thread();
            client.start();

        }

        int i;
        TCP_CounterClient counterClient = null;

        for (i = 0; i < 10; i++) {
            counterClient = new TCP_CounterClient();
            System.out.println("New client " + i + " created.");
            counterClient.main(new String[]{"localhost", "2"});


        }
        System.out.println(i + " cycles completed.");
    }
}


// TODO implement counter Client with Runnable in order to make Threads from it.