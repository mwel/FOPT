package rmi_sleep;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SleepServer {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            Sleeper sleepServer;
            sleepServer = new Sleeper();
            registry.rebind("SleepServer1", sleepServer);
            sleepServer = new Sleeper();
            registry.rebind("SleepServer2", sleepServer);
            System.out.println("Counter Server running.");
            System.out.println("Sleepers bound to registry.");

        } catch (RemoteException rE) {
            System.out.println(rE);
            rE.printStackTrace();
        }
    }
}
