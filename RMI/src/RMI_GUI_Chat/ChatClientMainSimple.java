package RMI_GUI_Chat;

import RMI_Chat.ChatClientSimple;
import RMI_Chat.ChatServer;
import RMI_Chat.ChatServerInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ChatClientMainSimple {

    public static void main(String[] args) {

        if (args.length != 3) {

            System.out.println("Necessary arguments not found: <name of chat group>");
            return;
        }

        try {
            ChatServer chatServer = (ChatServer) Naming.lookup("rmi://" + args[1] + "/" + args[2]);
            System.out.println("Request sent to server.");

            ChatClientSimple client = new ChatClientSimple(args[0]);

            if (chatServer.addClient(client)) {
                System.out.println("Exit by entering 'exit'.");
                sendInputToServer(chatServer, args[0]);
                chatServer.removeClient(client);
            } else {
                System.out.println("Name already taken. Please choose another.");
            }

        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            System.out.println(e);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    public static void sendInputToServer(ChatServerInterface chatServer, String name) throws IOException {

        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while ((line = input.readLine()) != null) {
            if (line.equals("exit")) {
                break;
            }
            chatServer.sendMessage(name, line);
        }


    }
}
