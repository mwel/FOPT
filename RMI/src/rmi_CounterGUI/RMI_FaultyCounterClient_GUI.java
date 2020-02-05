package rmi_CounterGUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class RMI_FaultyCounterClient_GUI extends Application {

    protected final RMI_SyncCounter counter;
    private final Label counterLabel;

    public RMI_FaultyCounterClient_GUI(final RMI_SyncCounter counter) {

        this.setDefaultCloseOperation(WindowEvent.WINDOW_CLOSE_REQUEST);

        this.counter = counter;
        this.counterLabel = new Label("");
        final Button incrementButton = new Button("Increment");
        final Button resetButton = new Button("Reset Count");

        // Hier wird über eine Methodenreferenz die Aktion vom Button einfach an die Methode weitergeleitet.
        incrementButton.setOnAction(this::clickedIncrementButton);
        resetButton.setOnAction(this::clickedResetButton);

        Group root = new Group();
        root.getChildren().add(counterLabel);
        root.getChildren().add(incrementButton);
        root.getChildren().add(resetButton);

    }

    protected void clickedIncrementButton(final ActionEvent event) {

        // try {
        final int counterValueAfterIncrement = this.counter.increment();
        this.counterLabel.setText(String.valueOf(counterValueAfterIncrement));

            /* } catch (final RemoteException rE) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initStyle(StageStyle.UTILITY);
            alert.setTitle("RemoteException");
            alert.setHeaderText(null);
            alert.setContentText("Error: " + rE.getMessage());
            alert.showAndWait();
        }*/
    }

    protected void clickedResetButton(final ActionEvent event) {
    }

    private void setDefaultCloseOperation(EventType<WindowEvent> disposeOnClose) {

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Group root = new Group();
        Scene scene = new Scene(root, 200, 400);

        primaryStage.setTitle("RMI Counter Server");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
