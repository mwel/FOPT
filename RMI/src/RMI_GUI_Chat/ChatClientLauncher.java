package RMI_GUI_Chat;

import RMI_Chat.ChatClientInterface;
import RMI_Chat.ChatServerInterface;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.rmi.Naming;
import java.util.List;

public class ChatClientLauncher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        List<String> args = getParameters().getUnnamed();
        String name = args.get(0);
        ChatClientPresenter presenter = new ChatClientPresenter();
        ChatClientView view = new ChatClientView(presenter);

        try {
            ChatServerInterface chatServer = (ChatServerInterface) Naming.lookup("rmi://" + args.get(1) + "/" + args.get(2));
            ChatClientInterface callBack = new ChatClient(name, view);

            if (!chatServer.addClient(callBack)) {

                System.out.println("Establishing connection failed.");
                System.exit(0);
            }
            presenter.setModelAndName(chatServer, name);
        } catch (Exception e) {
            System.out.println("Connection to server not possible.");
            System.exit(0);

        }

        Scene scene = new Scene(view.getUI());
        primaryStage.setScene(scene);
        primaryStage.setTitle(name);
        primaryStage.show();

    }

    public static void main(String[] args) {

        if (args.length != 3) {

            System.out.println("Please enter the correct chat server name.");
            Platform.exit();
            return;
        }

        launch(args);
        System.exit(0);
    }
}
