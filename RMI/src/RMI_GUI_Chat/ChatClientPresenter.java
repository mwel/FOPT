package RMI_GUI_Chat;

import RMI_Chat.ChatServerInterface;

import java.rmi.RemoteException;

public class ChatClientPresenter {

    private ChatServerInterface model;
    private String name;

    public void setModelAndName(ChatServerInterface model, String name) {
        this.model = model;
        this.name = name;
    }

    public void send(String message) {
        new Thread(() -> sendInThread(message)).start();
    }

    private void sendInThread(String message) {

        try {
            model.sendMessage(name, message);

        } catch (RemoteException e) {
            System.out.println("RMI ERROR");
            e.printStackTrace();
        }
    }
}
