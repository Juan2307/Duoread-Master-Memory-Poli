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

/**
 * Controlador para finalizar el juego del memorama.
 * Extiende la clase Application de JavaFX.
 */
public class VistaFinalController extends Application {

    //region Constructor

    /**
     * Constructor del controlador VistaFinalController
     */
    public VistaFinalController() {
    }

    //endregion

    //region Campos Privados

    /**
     * Etiqueta para mostrar el título del nivel
     */
    @FXML
    public Label title;

    /**
     * Fuente (no usada explícitamente en el código proporcionado)
     */
    @FXML
    public Font x2;

    /**
     * Botón para salir del juego
     */
    @FXML
    private Button btnSalir;

    /**
     * Fuente (no usada explícitamente en el código proporcionado)
     */
    @FXML
    public Font x1;

    /**
     *  Etiqueta para mostrar el mesaje de felicitaciones
     */
    @FXML
    public Label title1;

    //endregion

    //region Metodos Publicos

    /**
     * Método de finalizacion de la aplicación.
     * Este método es requerido por la clase Application, pero no se utiliza en este controlador.
     * @param primaryStage El escenario principal de la aplicación.
     */
    @Override
    public void start(Stage primaryStage) {
        // Este método no se utiliza en este controlador.
    }

    /**
     * Método que se ejecuta al salir del juego.
     * @param event El evento de acción que desencadena este método.
     */
    @FXML
    public void salirJuego(ActionEvent event) {
        // Ejecuta el código en el hilo de la aplicación JavaFX.
        Platform.runLater(() -> {
            try {
                // Crea una alerta de confirmación para salir del juego.
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Lobby");
                alert.setHeaderText("¿Desea salir del juego?");

                // Crea botones para las opciones de confirmar o cancelar.
                ButtonType buttonTypeYes = new ButtonType("Sí");
                ButtonType buttonTypeNo = new ButtonType("No");

                // Establece los botones en la alerta.
                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                // Muestra la alerta y espera la respuesta del usuario.
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == buttonTypeYes) {
                    // Si el usuario confirma, cierra la ventana actual.
                    Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                    stage.close();
                }
            } catch (Exception ex) {
                // Maneja cualquier error inesperado.
                System.err.println("Error inesperado: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }

    //endregion

}
