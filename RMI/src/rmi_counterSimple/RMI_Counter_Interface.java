package rmi_counterSimple;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMI_Counter_Interface extends Remote {

    String DEFAULT_RMI_OBJECT_NAME = "Counter"; // = "rmi://localhost:1099/Counter"

    int reset() throws RemoteException;

    int increment() throws RemoteException;

}
