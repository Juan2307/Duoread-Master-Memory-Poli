package com.example.duoreadmastermemorypoli;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class InicioVistaController extends Application {

    //region Campos Privados

    private Alert alert;

    //endregion

    //region Constructor

    public void setAlert(Alert alert) {
        this.alert = alert;
    }

    //endregion

    //region Métodos Públicos

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }

    @FXML
    public void iniciarJuego(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("JuegoVistaLevel1.fxml"));
            Pane juegoPane = loader.load();
            Scene juegoScene = new Scene(juegoPane);
            Stage stage = (Stage) ((Button) event.getSource()).getParent().getScene().getWindow();
            stage.setScene(juegoScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void salirJuego(ActionEvent event) {
        if (alert != null) { // Verifica que el Alert esté configurado
            alert.setTitle("Salir");
            alert.setHeaderText("¿Desea salir del juego?");

            ButtonType buttonTypeYes = new ButtonType("Sí");
            ButtonType buttonTypeNo = new ButtonType("No");

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeYes) {
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                stage.close();
            }
        } else {
            // Manejo alternativo si el Alert no está configurado
            System.out.println("Alert no configurado.");
        }
    }

    //endregion

    //region Métodos Privados

    //endregion

}

