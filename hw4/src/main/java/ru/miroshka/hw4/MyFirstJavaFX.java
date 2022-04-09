package ru.miroshka.hw4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * Домашнее задание №4 курс Java 2
 * @author Miroshnichenko Igor
 * @version 2.4
 * created on 2022-04-06
 */
public class MyFirstJavaFX extends Application {
    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {


        FXMLLoader fxmlLoader = new FXMLLoader(MyFirstJavaFX.class.getResource("views/Message-view.fxml"));
        stage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.setTitle("Шушуканье");
        this.stage = stage;
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}