package com.example.duoreadmastermemorypoli;

import javafx.animation.*;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JuegoVistaLevel1Controller extends Application {

    //region Campos Privados y Publicos

    public Timeline timeline;
    public int tiempoRestante;
    public List<String> imagenes;
    public List<Button> botonesCartas;
    public List<Button> cartasVolteadas;
    public int parejasRestantes;
    public boolean enProcesoDeComparacion = false;

    @FXML/**/
    public Label lblTiempo;

    @FXML
    public GridPane gridCartas;

    @FXML
    public Pane paneMensaje;

    @FXML
    public Label lblMensaje;

    @FXML
    public Button btnReiniciar;

    @FXML
    public Button btnIrLobby;

    @FXML
    public Button btnLobby;

    @FXML
    public Button btnSiguienteNivel;

    @FXML
    public Label lblTitulo;

    @FXML
    public Font x1;

    @FXML
    private Pane rootPane;

    //endregion

    //region Metodos Publicos

    public static void main(String[] args) {
        launch(args);
    }

    public void initialize() {
        iniciarTimerPreJuego();
        cargarImagenes();
    }

    @Override
    public void start(Stage primaryStage) {

    }

    @FXML
    public void volverLobby(ActionEvent event) {

        try {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Lobby");
            alert.setHeaderText("¿Desea volver al lobby?");

            ButtonType buttonTypeYes = new ButtonType("Sí");
            ButtonType buttonTypeNo = new ButtonType("No");

            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == buttonTypeYes) {

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InicioVista.fxml"));
                Pane ventana = fxmlLoader.load();
                Scene scene = new Scene(ventana);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();

            }

        }
        catch (IOException ex) {
            System.err.println("Error cargando el archivo FXML: " + ex.getMessage());
            ex.printStackTrace();
        }
        catch (Exception ex) {
            System.err.println("Error inesperado: " + ex.getMessage());
            ex.printStackTrace();
        }

    }

    @FXML
    public void reiniciarJuego(ActionEvent event) {

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
    public void irASiguienteNivel(ActionEvent event) {

        Platform.runLater(() -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("JuegoVistaLevel2.fxml"));
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

    //endregion

    //region Metodos Privados

    private void iniciarTimerPreJuego() {

        lblTitulo.setText("NIVEL 1");
        tiempoRestante = 4;

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {

            tiempoRestante--;

            if (tiempoRestante <= 0) {

                timeline.stop();
                iniciarJuego();
                iniciarTimer();
                return;

            }

            lblTiempo.setText("Tiempo: " + tiempoRestante);

        }));
        timeline.setCycleCount(tiempoRestante); // Cambiado a 4 para que se muestren 3 segundos
        timeline.play();

        // Deshabilitar la interacción con la interfaz durante el contador de prejuego
        gridCartas.setDisable(true);
        paneMensaje.setDisable(true);

    }

    private void cargarImagenes() {

        imagenes = new ArrayList<>();
        imagenes.add("/Img/nivel1/Father-Padre.png");
        imagenes.add("/Img/nivel1/Cousin-Primo.png");
        imagenes.add("/Img/nivel1/Grandson-Nieto.png");
        imagenes.add("/Img/nivel1/Uncle-Tio.png");
        imagenes.add("/Img/nivel1/Aunt-Tia.png");

        // Duplicar las imágenes para tener las parejas
        imagenes.addAll(imagenes);
        Collections.shuffle(imagenes);

    }

    private void iniciarJuego() {

        cartasVolteadas = new ArrayList<>();
        botonesCartas = new ArrayList<>();
        parejasRestantes = imagenes.size() / 2;

        // Habilitar la interacción con las cartas
        gridCartas.setDisable(false);

        for (int i = 0; i < imagenes.size(); i++) {

            // Crear un StackPane para cada carta
            StackPane stackPane = new StackPane();
            stackPane.setMinSize(100, 100);
            stackPane.setMaxSize(100, 100);

            // Crear un ImageView para la imagen por defecto de la carta
            Image imagenPorDefecto = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Img/Caratula/LogoPoli.png")));
            ImageView imageViewPorDefecto = new ImageView(imagenPorDefecto);
            imageViewPorDefecto.setFitWidth(100);
            imageViewPorDefecto.setFitHeight(100);

            // Agregar el ImageView por defecto al StackPane
            stackPane.getChildren().add(imageViewPorDefecto);

            // Crear la carta y asignar el StackPane como su contenido gráfico
            Button carta = new Button();
            carta.setGraphic(stackPane);

            final int indice = i;
            carta.setOnAction(event -> {

                try {
                    voltearCarta(carta, imageViewPorDefecto, indice);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            });

            botonesCartas.add(carta);

            gridCartas.add(carta, i % 5, i / 5);

        }

    }

    private void voltearCarta(Button carta, ImageView imageViewPorDefecto, int indice) throws IOException {

        if (enProcesoDeComparacion) {
            return;
        }

        // Deshabilitar la carta temporalmente
        carta.setDisable(true);

        // Crear el ImageView con la imagen correspondiente
        String rutaImagen = imagenes.get(indice);
        URL imagenUrl = getClass().getResource(rutaImagen);

        if (imagenUrl == null) {
            System.out.println("Imagen no encontrada: " + rutaImagen);
            carta.setDisable(false);
            return; // O manejar el error adecuadamente
        }

        ImageView imageView = new ImageView(new Image(imagenUrl.toString()));
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);

        // Tamaño fijo para la imagen de la carta volteada
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);

        RotateTransition rotacion1 = new RotateTransition(Duration.seconds(0.5), carta);
        rotacion1.setAxis(Rotate.Y_AXIS);
        rotacion1.setFromAngle(0);
        rotacion1.setToAngle(90);

        PauseTransition pausa = new PauseTransition(Duration.seconds(0.5));

        RotateTransition rotacion2 = new RotateTransition(Duration.seconds(0.5), carta);
        rotacion2.setAxis(Rotate.Y_AXIS);
        rotacion2.setFromAngle(90);
        rotacion2.setToAngle(0);

        ParallelTransition volteo = new ParallelTransition(rotacion1, rotacion2);
        volteo.setCycleCount(1);
        volteo.setOnFinished(event -> {

            // Mostrar la imagen de la carta volteada en lugar de la imagen por defecto
            StackPane stackPane = (StackPane) carta.getGraphic();
            stackPane.getChildren().clear(); // Limpiar el StackPane
            stackPane.getChildren().add(imageView);

            // Verificar si la carta está en la lista de cartas volteadas
            cartasVolteadas.add(carta);

            if (cartasVolteadas.size() == 2) {

                enProcesoDeComparacion = true;
                String rutaPrimeraCarta = (String) cartasVolteadas.get(0).getUserData();
                String rutaSegundaCarta = rutaImagen;

                if (rutaPrimeraCarta.equals(rutaSegundaCarta)) {

                    // Las cartas coinciden
                    for (Button cartaVolteada : cartasVolteadas) {
                        cartaVolteada.setDisable(true);
                    }
                    cartasVolteadas.clear();
                    parejasRestantes--;
                    enProcesoDeComparacion = false;
                    if (parejasRestantes == 0) {
                        try {
                            timeline.stop(); // Detener el temporizador
                            mostrarMensaje("¡Felicidades!", "Has ganado el juego.", true);
                        } catch (IOException ex) {
                            Logger.getLogger(JuegoVistaLevel1Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                } else {
                    // Las cartas no coinciden
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                        for (Button cartaVolteada : cartasVolteadas) {
                            cartaVolteada.setDisable(false);
                            StackPane sp = (StackPane) cartaVolteada.getGraphic();
                            sp.getChildren().clear(); // Limpiar el StackPane
                            sp.getChildren().add(imageViewPorDefecto);
                        }
                        cartasVolteadas.clear();
                        enProcesoDeComparacion = false;
                    }));
                    timeline.play();
                }
            } else {
                carta.setUserData(rutaImagen);
            }

        });
        volteo.play();

    }

    private void mostrarMensaje(String titulo, String mensaje, boolean esVictoria) throws IOException {

        lblMensaje.setText(mensaje);
        lblMensaje.setStyle("-fx-font-weight: bold;"); // Negrita para el mensaje

        paneMensaje.setDisable(false);
        paneMensaje.setMouseTransparent(false);
        paneMensaje.setVisible(true);

        if (esVictoria) {
            paneMensaje.setStyle("-fx-background-color: green; -fx-font-weight: bold;"); // Fondo verde para victoria
            btnSiguienteNivel.setVisible(true); // Mostrar el botón para ir al siguiente nivel
        } else {
            paneMensaje.setStyle("-fx-background-color: red; -fx-font-weight: bold;"); // Fondo rojo para tiempo agotado
            btnSiguienteNivel.setVisible(false); // Ocultar el botón para ir al siguiente nivel
        }

        gridCartas.setDisable(true);
        btnReiniciar.setDisable(false);
        btnIrLobby.setDisable(false);

    }

    private void iniciarTimer() {

        tiempoRestante = 31; // Cambiado a 31 para iniciar desde 30 segundos
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {

            tiempoRestante--;
            lblTiempo.setText("Tiempo: " + tiempoRestante);
            if (tiempoRestante <= 0) {
                timeline.stop();
                try {
                    mostrarMensaje("¡Time Over!", "Se acabó el tiempo. ¡Inténtalo de nuevo!", false);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    //endregion

}
