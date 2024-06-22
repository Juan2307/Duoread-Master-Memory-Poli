package com.example.duoreadmastermemorypoli;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para el controlador de la vista final del juego (VistaFinalController).
 * Utiliza TestFX para realizar pruebas de interfaz gráfica de usuario en JavaFX.
 */
class VistaFinalControllerTest extends ApplicationTest {

    private VistaFinalController controller;
    private Stage stage;

    /**
     * Método de inicio para configurar la escena de prueba.
     * Carga el archivo FXML y establece la escena en el stage para las pruebas.
     * @param stage El stage sobre el cual se establecerá la escena.
     * @throws Exception Si ocurre algún error durante la carga del archivo FXML.
     */
    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/duoreadmastermemorypoli/VistaFinal.fxml"));
        Pane root = fxmlLoader.load();
        controller = fxmlLoader.getController();

        Scene scene = new Scene(root);

        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });
    }

    /**
     * Método de configuración previo a cada prueba.
     * Aquí no se realiza ninguna configuración específica.
     */
    @BeforeEach
    public void setUp() {
        // No se requiere configuración previa para esta prueba
    }

    /**
     * Prueba unitaria para el método start() del controlador de la vista final.
     * Verifica que la escena se inicialice correctamente en el stage.
     */
    @Test
    public void testStartMethod() {
        controller.start(stage);

        // Verificar que la escena no sea nula
        Scene scene = stage.getScene();
        assertNotNull(scene);
    }

    /**
     * Prueba unitaria para el método salirJuego() del controlador de la vista final.
     * Simula hacer clic en el botón para salir del juego y verifica que la nueva escena se configure correctamente.
     */
    @Test
    void salirJuego() {
        // Simula hacer clic en el botón para salir del juego
        clickOn("#btnSalir");

        // Esperar un breve período de tiempo para que la nueva escena se cargue completamente
        sleep(1000); // Esperar 1 segundo (ajustar según sea necesario)

        // Simular clic en el botón "Sí" del Alert
        FxRobot robot = new FxRobot();
        robot.clickOn("Sí");

        // Verifica que la nueva escena está configurada correctamente después de cerrar el Alert
        Node root = stage.getScene().getRoot();
        assertNotNull(root);
    }

}
