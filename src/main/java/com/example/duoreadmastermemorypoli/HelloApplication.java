package com.example.duoreadmastermemorypoli;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    //region Metodos Privados

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InicioVista.fxml"));
        Pane ventana = fxmlLoader.load();
        Scene scene = new Scene(ventana);
        stage.setScene(scene);
        stage.setTitle("Memorama");
        stage.show();

    }

    //endregion

}