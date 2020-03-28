package RMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Demo extends Remote {

    public void increment(Data data) throws RemoteException;
}
