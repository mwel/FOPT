import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Listing43 extends Application {
    private Label label;
    private int counter;

    private void increment() {

        Thread t = new Thread(() -> incrementAsync());
        t.setDaemon(true);
        t.start();
    }

    private void incrementAsync() {

        for (int i = 1; i <= 10; i++) {
            Platform.runLater(() -> reallyIncrement());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }

    private void reallyIncrement() {
        counter++;
        String message = "" + counter;
        System.out.println(message);
        label.setText(message);
    }

    private void reset() {
        counter = 0;
        label.setText("" + counter);
    }

    public void start(Stage primaryStage) {
        label = new Label();
        Button b1 = new Button("Erhöhen");
        b1.setOnAction(e -> increment());
        Button b2 = new Button("Zurücksetzen");

        b2.setOnAction(e -> reset());
        VBox root = new VBox();
        root.getChildren().add(label);
        root.getChildren().add(b1);
        root.getChildren().add(b2);
        reset();
        Scene scene = new Scene(root, 200, 70);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zähler");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}