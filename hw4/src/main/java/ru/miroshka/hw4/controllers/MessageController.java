package ru.miroshka.hw4.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import ru.miroshka.hw4.Message;
import ru.miroshka.hw4.MyFirstJavaFX;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import static javafx.scene.control.PopupControl.USE_PREF_SIZE;
import static javafx.scene.input.KeyCode.ENTER;


public class MessageController {

    private final Image micImage;
    private final Image sendImage;
    //public VBox vbox;

    private boolean flagMessage;

    @FXML
    public ListView<MessageInOutController> messagesListView;

    @FXML
    protected TextArea messageField;

    @FXML
    protected ImageView micSend;

    public MessageController() {
        String localDir = System.getProperty("user.dir") + "\\target\\classes\\ru\\miroshka\\hw4\\img";
        sendImage = new Image(localDir + "\\send.png");
        micImage = new Image(localDir + "\\mic.png");
        flagMessage = false;
        messagesListView = new ListView<>();

    }


    /**
     * Отправка сообщения при нажатии на 'Enter'
     *
     * @param keyEvent
     */
    public void editMessage(KeyEvent keyEvent) {
        SendOrMic(keyEvent);
        if (flagMessage){
            if (keyEvent.getCode() == ENTER) {
                sendMessage();
            }
        }



    }

    private void SendOrMic(KeyEvent keyEvent){
        if (!(messageField.getText() + keyEvent.getText()).isEmpty()) {
            micSend.setImage(sendImage);
            flagMessage = true;

        } else {
            micSend.setImage(micImage);
            flagMessage = false;
        }
    }

    private void sendMessage() {
        if (flagMessage) {
            try {
                Message msg = new Message.Builder()
                        .nickName(System.getProperty("user.name"))
                        .isImage(false)
                        .isOutgoing(true)
                        .text(messageField.getText())
                        .time(getCurrentTime())
                        .build();

                MessageInOutController mioc = new MessageInOutController();
                mioc.updateItem(msg, false);
                mioc.setWrapText(true);
                mioc.setMaxHeight(150);

                //размер сообщения по ширине
                mioc.setPrefWidth(mioc.getMessageLabelSize());

                messagesListView.getItems().add(mioc);
                messagesListView.scrollTo(messagesListView.getItems().size());
                messagesListView.refresh();
                messageField.clear();
                micSend.setImage(micImage);
                flagMessage = false;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void sendMessageClicked(MouseEvent mouseEvent) {
        sendMessage();
    }


    /**
     * Подготавливает сообщение (к отправке и логированию)
     */
    private Message prepareMessage() {
        String name;
        try {
            name = InetAddress.getLocalHost().getHostName();
        } catch (Exception exp) {
            exp.printStackTrace(System.out);
            //в дальнейшем будет нинейм пользователя, если он скрыт то id/номер телефона
            name = "YOU";
        }
        Message msg = new Message.Builder().nickName(name).text(this.messageField.getText()).build();

        return msg;
    }

    public static String getCurrentTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public void searchChatRoom(MouseEvent mouseEvent) {
        //todo поиск
    }

    @FXML
    void closeApp(MouseEvent event) {
        MyFirstJavaFX.stage.close();
    }

    @FXML
    void minimizeApp(MouseEvent event) {
        MyFirstJavaFX.stage.setIconified(true);
    }

    public void settingsButtonClicked(MouseEvent mouseEvent) {
    }

    @FXML
    void attachFile(MouseEvent event) {
        //todo - прикрепить файл
    }

    public void smileyButtonClicked(MouseEvent mouseEvent) {
        //todo - список смайлов
    }

    public void vocalMessageClicked(MouseEvent mouseEvent) {
        //todo - запись голосового сообщения
    }

    public void slideMenuClicked(MouseEvent mouseEvent) {
        // todo - вызов view с настройками
    }

}