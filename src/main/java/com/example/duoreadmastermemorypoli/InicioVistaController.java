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

/**
 * Controlador para la vista de inicio del juego de memorama.
 * Extiende la clase Application de JavaFX.
 */
public class InicioVistaController extends Application {

    //region Campos Privados

    private Alert alert; // Alerta utilizada para confirmar la salida del juego

    //endregion

    //region Métodos Públicos

    /**
     * Método de inicio de la aplicación.
     * Este método es requerido por la clase Application, pero no se utiliza en este controlador.
     * @param primaryStage El escenario principal de la aplicación.
     */
    @Override
    public void start(Stage primaryStage) {
        // Este método no se utiliza en este controlador.
    }

    /**
     * Método que se ejecuta al iniciar el juego.
     * @param event El evento de acción que desencadena este método.
     */
    @FXML
    public void iniciarJuego(ActionEvent event) {
        // Ejecuta el código en el hilo de la aplicación JavaFX.
        Platform.runLater(() -> {
            try {
                // Carga el archivo FXML para el nivel 1 del juego.
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("JuegoVistaLevel1.fxml"));
                // Carga el contenido del archivo FXML en un objeto Pane.
                Pane ventana = fxmlLoader.load();
                // Crea una nueva escena con el contenido del Pane.
                Scene scene = new Scene(ventana);
                // Obtiene el escenario actual desde el evento.
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                // Establece la nueva escena en el escenario.
                stage.setScene(scene);
                // Muestra la nueva escena.
                stage.show();
            } catch (IOException ex) {
                // Maneja errores de entrada/salida al cargar el archivo FXML.
                System.err.println("Error cargando el archivo FXML: " + ex.getMessage());
                ex.printStackTrace();
            } catch (Exception ex) {
                // Maneja cualquier otro error inesperado.
                System.err.println("Error inesperado: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
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
