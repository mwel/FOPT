package RMI_CounterBookGUI;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ClientGuiView {

    private ClientGuiPresenter presenter;
    private VBox root;
    private Label label;

    public ClientGuiView(ClientGuiPresenter presenter) {

        this.presenter = presenter;
        initView();
    }

    private void initView() {

        root = new VBox();
        Insets padding = new Insets(10);
        root.setPadding(padding);

        label = new Label();
        label.setMaxWidth(Double.MAX_VALUE);
        root.getChildren().add(label);

        Button increaseButton = new Button("Increase");
        increaseButton.setMaxWidth(Double.MAX_VALUE);
        increaseButton.setOnAction(event -> presenter.increment());
        root.getChildren().add(increaseButton);

        Button resetButton = new Button("Reset");
        resetButton.setMaxWidth(Double.MAX_VALUE);
        resetButton.setOnAction(event -> presenter.reset());
        root.getChildren().add(resetButton);

    }

    public Pane getUI() {

        return root;
    }

    public void showCounter(int counter) {

        label.setText("" + counter);
    }
}
