package RMI_CounterBookSimple;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Counter extends Remote {

    public int increment() throws RemoteException;

    public int reset() throws RemoteException;

}
