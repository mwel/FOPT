package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMI_Server {

    public static void main(String[] args) {

        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            DemoImplementation demo = new DemoImplementation();
            registry.rebind("Demo", demo);
            System.out.println("Registry created and stub registered.");

        } catch (Exception e) {
            System.out.println("Failed to bind to registry.");
        }
    }
}
