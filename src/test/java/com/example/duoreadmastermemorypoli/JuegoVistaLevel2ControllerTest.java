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

class JuegoVistaLevel2ControllerTest extends ApplicationTest {

    private JuegoVistaLevel2Controller controller;
    private Stage stage;

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/duoreadmastermemorypoli/JuegoVistaLevel2.fxml"));
        Pane root = fxmlLoader.load();
        controller = fxmlLoader.getController();

        Scene scene = new Scene(root);

        Platform.runLater(() -> {
            stage.setScene(scene);
            stage.show();
        });

    }

    @BeforeEach
    public void setUp() {

        assertNotNull(controller.btnReiniciar);
        assertNotNull(controller.btnSiguienteNivel);
        assertNotNull(controller.btnIrLobby);
        //assertNotNull(controller.btnLobby);

    }

    @Test
    void initialize() {

        // Verificar que initialize no lance excepciones
        assertDoesNotThrow(() -> controller.initialize());

    }

    @Test
    void start() {

        controller.start(stage);

        // Verificar que la escena no sea nula
        Scene scene = stage.getScene();
        assertNotNull(scene);

    }

    @Test
    void volverLobby() {

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

    @Test
    void reiniciarJuego() {

        clickOn("#btnReiniciar");

        // Verifica que la nueva escena está configurada correctamente
        Node root = stage.getScene().getRoot();
        assertNotNull(root);

    }

    @Test
    void irASiguienteNivel() {

        controller.btnSiguienteNivel.setVisible(true);
        clickOn("#btnSiguienteNivel");

        // Verificar que la nueva escena está configurada correctamente
        Node root = stage.getScene().getRoot();
        assertNotNull(root);

    }

}