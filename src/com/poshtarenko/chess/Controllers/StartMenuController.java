package com.poshtarenko.chess.Controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class StartMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    void exit(ActionEvent event) {

    }

    @FXML
    void startGame(ActionEvent event) {
        try {
            URL url = new File("src/resources/fxml/game.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url, resources);
            Stage stage = new Stage();
            url = new File("src/resources/images/icon/icon.png").toURI().toURL();
            stage.getIcons().add(new Image(String.valueOf(url)));
            stage.setTitle("Шахматы");
            stage.setScene(new Scene(root, 1100, 700));
            stage.setResizable(false);
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }
}
