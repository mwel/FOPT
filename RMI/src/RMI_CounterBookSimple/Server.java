package RMI_CounterBookSimple;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {

        try {

            // Hier wird die RMI Registry gestartet.
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("Counter", new CounterImplementation());

            // Durch obenstehenden Code ersetzt.
            // CounterImplementation counter = new CounterImplementation();
            // Naming.rebind("Counter", counter);

            System.out.println("Server running and counter bound to registry.");

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
