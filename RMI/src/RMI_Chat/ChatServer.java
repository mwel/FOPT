package RMI_Chat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;

public class ChatServer extends UnicastRemoteObject implements ChatServerInterface {

    private ArrayList<ChatClientInterface> clientList;

    public ChatServer() throws RemoteException {

        clientList = new ArrayList<ChatClientInterface>();

    }


    @Override
    public synchronized boolean addClient(ChatClientInterface objRef) throws RemoteException {

        String name = objRef.getName();
        for (Iterator<ChatClientInterface> iterator = clientList.iterator(); iterator.hasNext(); ) {
            ChatClientInterface chatClientInterface = iterator.next();
            try {
                if (chatClientInterface.getName().equals(name)) {
                    return false;
                }
            } catch (RemoteException e) {
                iterator.remove();
                e.printStackTrace();
            }
        }
        clientList.add(objRef);
        return true;

    }

    @Override
    public synchronized void removeClient(ChatClientInterface objRef) throws RemoteException {

        clientList.remove(objRef);

    }

    @Override
    public synchronized void sendMessage(String name, String message) throws RemoteException {

        for (Iterator<ChatClientInterface> iterator = clientList.iterator(); iterator.hasNext(); ) {

            ChatClientInterface chatClientInterface = iterator.next();

            try {
                chatClientInterface.print(name + ": " + message);

            } catch (RemoteException e) {
                iterator.remove();
                e.printStackTrace();
            }

        }

    }
}
