package RMI_GUI_Chat;

import RMI_Chat.ChatClientInterface;
import javafx.application.Platform;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChatClient extends UnicastRemoteObject implements ChatClientInterface {

    private String name;
    private ChatClientView view;

    protected ChatClient(String name, ChatClientView view) throws RemoteException {

        this.name = name;
        this.view = view;
    }

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public void print(String message) throws RemoteException {

        Platform.runLater(() -> view.showMessage());

    }
}
