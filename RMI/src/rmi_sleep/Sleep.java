package rmi_sleep;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Sleep extends Remote {

    public void sleep(int secs) throws RemoteException;
}
