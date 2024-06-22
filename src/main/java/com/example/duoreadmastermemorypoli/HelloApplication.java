package com.example.duoreadmastermemorypoli;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Clase principal del juego de memorama.
 * Extiende la clase Application de JavaFX.
 */
public class HelloApplication extends Application {

    //region Metodos Privados

    /**
     * Método principal de la aplicación.
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String[] args) {
        // Lanza la aplicación JavaFX.
        launch();
    }

    /**
     * Método de inicio de la aplicación.
     * Este método es llamado cuando la aplicación JavaFX es iniciada.
     * @param stage El escenario principal de la aplicación.
     * @throws IOException Si ocurre un error durante la carga del archivo FXML.
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Carga el archivo FXML que define la vista inicial.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InicioVista.fxml"));
        // Carga el contenido del archivo FXML en un objeto Pane.
        Pane ventana = fxmlLoader.load();
        // Crea una nueva escena con el contenido del Pane.
        Scene scene = new Scene(ventana);
        // Establece la escena en el escenario principal.
        stage.setScene(scene);
        // Establece el título de la ventana.
        stage.setTitle("Memorama");
        // Muestra la ventana.
        stage.show();
    }

    //endregion

}
