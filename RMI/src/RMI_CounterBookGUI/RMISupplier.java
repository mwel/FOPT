package RMI_CounterBookGUI;

import java.rmi.RemoteException;

public interface RMISupplier<T> {

    public T execute() throws RemoteException;
}
