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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class InicioVistaController extends Application {

    //region Campos Privados

    private Alert alert;

    //endregion

    //region Métodos Públicos

    @Override
    public void start(Stage primaryStage) {

    }

    @FXML
    public void iniciarJuego(ActionEvent event) {

        Platform.runLater(() -> {
            try {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("JuegoVistaLevel1.fxml"));
                Pane ventana = fxmlLoader.load();
                Scene scene = new Scene(ventana);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                System.err.println("Error cargando el archivo FXML: " + ex.getMessage());
                ex.printStackTrace();
            } catch (Exception ex) {
                System.err.println("Error inesperado: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

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

