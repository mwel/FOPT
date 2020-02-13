package RMI_CounterBookGUI;

import RMI_CounterBookSimple.Counter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.rmi.Naming;
import java.util.List;

public class ClientGui extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Hiermit holt man sich die in der Kommandozeile übergebenen Parameter
        List<String> args = getParameters().getUnnamed();

        Counter model = null;

        try {
            model = (Counter) Naming.lookup("rmi://" + args.get(0) + "/" + args.get(1));
        } catch (Exception e) {
            System.out.println("Connection not possible.");
            Platform.exit();
        }

        ClientGuiPresenter presenter = new ClientGuiPresenter();
        ClientGuiView view = new ClientGuiView(presenter);
        presenter.setModelAndView(model, view);

        Scene scene = new Scene(view.getUI());
        primaryStage.setTitle("RMI Counter");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {

//        if (args.length != 2) {
//
//            System.out.println("Arguments: Server RMI-Object");
//            Platform.exit();
//            return;
//
//        }

        launch("localhost", "Counter");
    }
}
