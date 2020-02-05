import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class DrawingLines extends Application {

    private Pane graphicsPane;
    private double x, y;

    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();
        graphicsPane = new Pane();
        root.setCenter(graphicsPane);
        HBox hBox = new HBox(20);
        Button button = new Button("Delete");
        hBox.getChildren().add(button);
        hBox.getChildren().add(new Label("Lorem Ipsum..."));

        hBox.setPadding(new Insets(10));
        root.setBottom(hBox);

        graphicsPane.setOnMousePressed(e -> mousePressed(e.getX(), e.getY()));
        graphicsPane.setOnMouseDragged(e -> mouseDragged(e.getX(), e.getY()));
        button.setOnAction(e -> clear());

        Rectangle clipRect = new Rectangle();
        clipRect.widthProperty().bind(graphicsPane.widthProperty());
        clipRect.heightProperty().bind(graphicsPane.heightProperty());
        graphicsPane.setClip(clipRect);

        primaryStage.setTitle("Drawing Lines");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    private void clear() {
        graphicsPane.getChildren().clear();
    }

    private void mouseDragged(double newX, double newY) {
        graphicsPane.getChildren().add(new Line(x, y, newX, newY));
        x = newX;
        y = newY;
    }

    private void mousePressed(double newX, double newY) {
        x = newX;
        y = newY;
        mouseDragged(x, y);
    }

    public static void main(String[] args) {
        launch(args);

    }
}