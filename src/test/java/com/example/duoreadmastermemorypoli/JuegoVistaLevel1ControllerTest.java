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
 * Clase de prueba para el controlador del nivel 1 del juego (JuegoVistaLevel1Controller).
 * Utiliza TestFX para realizar pruebas de interfaz gráfica de usuario en JavaFX.
 */
public class JuegoVistaLevel1ControllerTest extends ApplicationTest {

    private JuegoVistaLevel1Controller controller;
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

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/duoreadmastermemorypoli/JuegoVistaLevel1.fxml"));
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
     * Verifica que los botones necesarios para la prueba no sean nulos.
     */
    @BeforeEach
    public void setUp() {
        assertNotNull(controller.btnReiniciar);
        assertNotNull(controller.btnSiguienteNivel);
        assertNotNull(controller.btnIrLobby);
        // assertNotNull(controller.btnLobby); // Este botón está comentado porque no se usa actualmente en las pruebas
    }

    /**
     * Prueba unitaria para el método initialize() del controlador del nivel 1.
     * Verifica que initialize() no lance excepciones.
     */
    @Test
    public void testInitializeMethod() {
        assertDoesNotThrow(() -> controller.initialize());
    }

    /**
     * Prueba unitaria para el método start() del controlador del nivel 1.
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
     * Prueba unitaria para el método irALobby() del controlador del nivel 1.
     * Simula hacer clic en el botón para ir al lobby y verifica que la nueva escena se configure correctamente.
     */
    @Test
    public void testIrALobby() {
        // Simula hacer clic en el botón para ir al lobby
        clickOn("#btnLobby");

        // Esperar un breve período de tiempo para que la nueva escena se cargue completamente
        sleep(1000); // Esperar 1 segundo (ajustar según sea necesario)

        // Simular clic en el botón "Sí" del Alert
        FxRobot robot = new FxRobot();
        robot.clickOn("Sí");

        // Verifica que la nueva escena está configurada correctamente después de cerrar el Alert
        Node root = stage.getScene().getRoot();
        assertNotNull(root);
    }

    /**
     * Prueba unitaria para el método reiniciarJuego() del controlador del nivel 1.
     * Simula hacer clic en el botón para reiniciar el juego y verifica que la nueva escena se configure correctamente.
     */
    @Test
    public void testReiniciarJuego() {
        clickOn("#btnReiniciar");

        // Verifica que la nueva escena está configurada correctamente
        Node root = stage.getScene().getRoot();
        assertNotNull(root);
    }

    /**
     * Prueba unitaria para el método irASiguienteNivel() del controlador del nivel 1.
     * Simula hacer clic en el botón para ir al siguiente nivel y verifica que la nueva escena se configure correctamente.
     */
    @Test
    public void testIrASiguienteNivel() {
        // Hacer visible el botón de siguiente nivel para la prueba
        controller.btnSiguienteNivel.setVisible(true);

        // Simula hacer clic en el botón para ir al siguiente nivel
        clickOn("#btnSiguienteNivel");

        // Verificar que la nueva escena está configurada correctamente
        Node root = stage.getScene().getRoot();
        assertNotNull(root);
    }

}


