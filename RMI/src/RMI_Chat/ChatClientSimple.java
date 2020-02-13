package RMI_Chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientSimple extends UnicastRemoteObject implements ChatClientInterface {

    private String name;

    public ChatClientSimple(String name) throws RemoteException {

        this.name = name;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public void print(String message) throws RemoteException {

        System.out.println(message);

    }
}
