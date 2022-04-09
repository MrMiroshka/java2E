package ru.miroshka.hw4.controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import ru.miroshka.hw4.Message;
import ru.miroshka.hw4.MyFirstJavaFX;

import java.io.IOException;

public class MessageInOutController extends ListCell<Message> {

    @FXML
    private GridPane root;


    @FXML
    private Label messageLabel;

    @FXML
    private Label messageTimeLabel;

    public double getMessageLabelSize() {
        double weight;
        String[] s = messageLabel.getText().split("\n");
        int max = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i].length() > max) {
                max = i;
            }
        }
        max = s[max].length() * 10 + messageTimeLabel.getText().length()*19;

        return (max>850?750:max);
    }

    public MessageInOutController() {
        this.root = new GridPane();
        this.messageLabel = new Label();
        this.messageTimeLabel = new Label();

    }

    @Override
    protected void updateItem(Message item, boolean empty) {
        super.updateItem(item, empty);
        FXMLLoader loader;
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (item.isOutgoing()) {
                if (!item.isImage()) {

                    loader = new FXMLLoader(MyFirstJavaFX.class.getResource("views/outgoing_message_view.fxml"));
                    loader.setController(this);
                    try {
                        loader.load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }


            messageTimeLabel.setText(item.getTime());
            if (!item.isImage()) {
                messageLabel.setText(item.getMessage());
            }
            setGraphic(root);

        }
    }

}