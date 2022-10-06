package com.poshtarenko.chess;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.*;

public class Main extends Application  {

    @Override
    public void start(Stage stage) throws IOException {
        URL url = new File("resources/fxml/game.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        url = new File("resources//images/icon/icon.png").toURI().toURL();
        stage.getIcons().add(new Image(String.valueOf(url)));
        stage.setTitle("Шахматы");
        stage.setScene(new Scene(root, 1100, 700));
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        launch(args);
    }
}
