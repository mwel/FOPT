package RMI_Chat;

import java.rmi.RemoteException;

public interface ChatServerInterface {

    public boolean addClient(ChatClientInterface objRef) throws RemoteException;

    public void removeClient(ChatClientInterface objRef) throws RemoteException;

    public void sendMessage(String name, String message) throws RemoteException;

}
