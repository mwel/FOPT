package RMI_Chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClientInterface extends Remote {

    public String getName() throws RemoteException;

    public void print(String message) throws RemoteException;
}
