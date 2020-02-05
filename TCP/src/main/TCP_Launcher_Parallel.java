package main;

import clients.TCP_ParallelDynamicClient;

public class TCP_Launcher_Parallel {

    public static void main(String[] args) {

        for (int i = 0; i < 100; i++) {
            TCP_ParallelDynamicClient parallelDynamicClient = new TCP_ParallelDynamicClient();
            parallelDynamicClient.main(new String[]{"localhost", "reset", "increment", "increment", "increment"});
        }
    }
}

