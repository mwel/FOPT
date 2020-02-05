import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SimpleCounter extends Application {
    private Label labelCount;
    private Label labelIncrement;
    private int counter;
    private int increment;

    // Teil für die Ereignisbehandlung
    private void increase() {
        increment++;
        labelIncrement.setText("Increment Size: " + increment);
    }

    private void counter(int increment) {
        counter = counter + increment;
        labelCount.setText("Count: " + counter);
    }

    private void reset() {
        counter = 0;
        labelCount.setText("Count: " + counter);
    }

    // Initialisierungsteil
    public void start(Stage primaryStage) {

        labelCount = new Label();
        labelIncrement = new Label();

        Button b1 = new Button("Add Increment");
        b1.setOnAction(e -> counter(increment));

        Button b2 = new Button("Reset");
        b2.setOnAction(e -> reset());

        Button b3 = new Button("Increase");
        b3.setOnAction((e -> increase()));

        VBox root = new VBox();
        root.getChildren().add(labelCount);
        root.getChildren().add(labelIncrement);
        root.getChildren().add(b1);
        root.getChildren().add(b2);
        root.getChildren().add(b3);

        reset();

        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Icremental Increase");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
