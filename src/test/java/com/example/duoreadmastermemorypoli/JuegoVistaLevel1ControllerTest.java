package com.example.duoreadmastermemorypoli;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JuegoVistaLevel1ControllerTest extends ApplicationTest {

    private JuegoVistaLevel1Controller controller;
    private Stage stage;

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

    @BeforeEach
    public void setUp() {
        assertNotNull(controller.btnReiniciar);
        assertNotNull(controller.btnIrLobby);
        assertNotNull(controller.btnSiguienteNivel);
    }

    @Test
    public void testReiniciarJuego() {

        clickOn("#btnReiniciar");

        // Verifica que la nueva escena está configurada correctamente
        Node root = stage.getScene().getRoot();
        assertNotNull(root);

    }

    @Test
    public void testirASiguienteNivelDelJuego() {

        controller.btnSiguienteNivel.setVisible(true);
        clickOn("#btnSiguienteNivel");

        // Verificar que la nueva escena está configurada correctamente
        Node root = stage.getScene().getRoot();
        assertNotNull(root);

    }

    @Test
    public void testVolverLobby() {

        clickOn("#btnIrLobby");

        // Espera a que aparezca el diálogo de alerta
        FxRobot robot = new FxRobot();
        robot.interact(() -> {
            // Verifica que se muestra un Alert
            Scene scene = stage.getScene();
            Optional<Alert> alert = scene.getWindow().getScene().getRoot().getChildrenUnmodifiable().stream()
                    .filter(node -> false)
                    .map(Alert.class::cast)
                    .findFirst();

            throw new AssertionError("No se encontró el diálogo de alerta");

        });

        // Verificar que la nueva escena está configurada correctamente
        Node root = stage.getScene().getRoot();
        assertNotNull(root);

    }

}


