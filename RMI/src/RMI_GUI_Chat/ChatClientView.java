package RMI_GUI_Chat;

import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class ChatClientView {

    private ChatClientPresenter presenter;
    private BorderPane root;
    private TextField input;
    private TextArea output;


    public ChatClientView(ChatClientPresenter presenter) {
        this.presenter = presenter;
        initView();
    }

    private void initView() {

        root = new BorderPane();
        Insets padding = new Insets(10);
        root.setPadding(padding);

        input = new TextField();
        input.setOnAction(event -> handleInput());
        root.setTop(input);
        BorderPane.setMargin(input, padding);

        output = new TextArea();
        output.setEditable(false);
        root.setCenter(output);
        BorderPane.setMargin(output, padding);

    }

    private void handleInput() {

        String message = input.getText();
        input.setText("");
        presenter.send(message);

    }

    public Pane getUI() {
        return root;
    }

    public void showMessage(String message) {

        output.appendText(message + "\n");
    }


    public void showMessage() {

    }
}
