package com.example.duoreadmastermemorypoli;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class VistaFinalController extends Application {

    //region Campos Privados

    @FXML
    private Label title;

    @FXML
    private Font x2;

    @FXML
    private Button btnSalir;

    @FXML
    private Font x1;

    @FXML
    private Label title1;

    //endregion

    //region Metodos Publicos

    @Override
    public void start(Stage primaryStage) {

    }

    @FXML
    public void salirJuego(ActionEvent event) {

        Platform.runLater(() -> {

            try {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Lobby");
                alert.setHeaderText("¿Desea salir del juego?");

                ButtonType buttonTypeYes = new ButtonType("Sí");
                ButtonType buttonTypeNo = new ButtonType("No");

                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == buttonTypeYes) {

                    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    stage.close();

                }

            }
            catch (Exception ex) {
                System.err.println("Error inesperado: " + ex.getMessage());
                ex.printStackTrace();
            }

        });

    }

    //endregion

}
