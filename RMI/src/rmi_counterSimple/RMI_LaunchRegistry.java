package rmi_counterSimple;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RMI_LaunchRegistry {

    public static void main(String[] args) throws RemoteException {

        LocateRegistry.createRegistry(1250);

    }
}
