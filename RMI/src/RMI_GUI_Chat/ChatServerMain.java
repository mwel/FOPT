package RMI_GUI_Chat;

import RMI_Chat.ChatServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class ChatServerMain {

    public static void main(String[] args) {

        try {
            ChatServer chatServer = new ChatServer();
            Naming.rebind("ChatServer", chatServer);

        } catch (RemoteException | MalformedURLException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }
}
