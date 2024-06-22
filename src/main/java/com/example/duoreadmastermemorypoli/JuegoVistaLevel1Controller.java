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

/**
 * Controlador para el nivel 1 del juego de memorama.
 * Extiende la clase Application de JavaFX.
 */
public class JuegoVistaLevel1Controller extends Application {

    //region Constructor

    /**
     * Constructor del controlador JuegoVistaLevel1Controller
     */
    public JuegoVistaLevel1Controller() {
    }

    //endregion

    //region Campos Privados y Publicos

    /**
     * Línea de tiempo para el temporizador del juego
     */
    public Timeline timeline;

    /**
     * Tiempo restante en el temporizador
     */
    public int tiempoRestante;

    /**
     * Lista de rutas de las imágenes de las cartas
     */
    public List<String> imagenes;

    /**
     * Lista de cartas que están volteadas actualmente
     */
    public List<Button> cartasVolteadas;

    /**
     * Lista de botones que representan las cartas
     */
    public List<Button> botonesCartas;

    /**
     * Número de parejas restantes para completar el nivel
     */
    public int parejasRestantes;

    /**
     * Indicador de si hay una comparación en proceso
     */
    public boolean enProcesoDeComparacion = false;

    /**
     * Etiqueta para mostrar el tiempo restante
     */
    @FXML
    public Label lblTiempo;

    /**
     * Pane para mostrar mensajes al jugador
     */
    @FXML
    public GridPane gridCartas;

    /**
     * Pane para mostrar mensajes al jugador
     */
    @FXML
    public Pane paneMensaje;

    /**
     *  Etiqueta para mostrar mensajes al jugador
     */
    @FXML
    public Label lblMensaje;

    /**
     *  Botón para reiniciar el juego
     */
    @FXML
    public Button btnReiniciar;

    /**
     * Botón para volver al lobby
     */
    @FXML
    public Button btnIrLobby;

    /**
     *   Otro botón para volver al lobby (posiblemente redundante)
     */
    @FXML
    public Button btnLobby;

    /**
     *  Botón para reiniciar el juego
     */
    @FXML
    public Button btnSiguienteNivel;

    /**
     * Etiqueta para mostrar el título del nivel
     */
    @FXML
    public Label lblTitulo;

    /**
     * Fuente (no usada explícitamente en el código proporcionado)
     */
    @FXML
    public Font x1;

    //endregion

    //region Metodos Publicos

    /**
     * Método que se llama al inicializar el controlador.
     */
    public void initialize() {
        iniciarTimerPreJuego(); // Inicia el temporizador previo al juego
        cargarImagenes(); // Carga las imágenes para las cartas
    }

    @Override
    public void start(Stage primaryStage) {
        // Método requerido por la clase Application, pero no utilizado en este controlador
    }

    /**
     * Método para volver al lobby.
     * @param event El evento de acción que desencadena este método.
     */
    @FXML
    public void volverLobby(ActionEvent event) {
        Platform.runLater(() -> {
            try {
                // Crear una alerta de confirmación para volver al lobby
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Lobby");
                alert.setHeaderText("¿Desea volver al lobby?");
                ButtonType buttonTypeYes = new ButtonType("Sí");
                ButtonType buttonTypeNo = new ButtonType("No");
                alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == buttonTypeYes) {
                    // Si el usuario confirma, cargar la vista del lobby
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InicioVista.fxml"));
                    Pane ventana = fxmlLoader.load();
                    Scene scene = new Scene(ventana);
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.show();
                }
            } catch (IOException ex) {
                // Manejar errores de carga del archivo FXML
                System.err.println("Error cargando el archivo FXML: " + ex.getMessage());
                ex.printStackTrace();
            } catch (Exception ex) {
                // Manejar cualquier otro error inesperado
                System.err.println("Error inesperado: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }

    /**
     * Método para reiniciar el juego.
     * @param event El evento de acción que desencadena este método.
     */
    @FXML
    public void reiniciarJuego(ActionEvent event) {
        Platform.runLater(() -> {
            try {
                // Cargar la vista del nivel 1 nuevamente
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("JuegoVistaLevel1.fxml"));
                Pane ventana = fxmlLoader.load();
                Scene scene = new Scene(ventana);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                // Manejar errores de carga del archivo FXML
                System.err.println("Error cargando el archivo FXML: " + ex.getMessage());
                ex.printStackTrace();
            } catch (Exception ex) {
                // Manejar cualquier otro error inesperado
                System.err.println("Error inesperado: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }

    /**
     * Método para ir al siguiente nivel.
     * @param event El evento de acción que desencadena este método.
     */
    @FXML
    public void irASiguienteNivel(ActionEvent event) {
        Platform.runLater(() -> {
            try {
                // Cargar la vista del nivel 2
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("JuegoVistaLevel2.fxml"));
                Pane ventana = fxmlLoader.load();
                Scene scene = new Scene(ventana);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                // Manejar errores de carga del archivo FXML
                System.err.println("Error cargando el archivo FXML: " + ex.getMessage());
                ex.printStackTrace();
            } catch (Exception ex) {
                // Manejar cualquier otro error inesperado
                System.err.println("Error inesperado: " + ex.getMessage());
                ex.printStackTrace();
            }
        });
    }

    //endregion

    //region Metodos Privados

    /**
     * Método para iniciar el temporizador previo al juego.
     */
    void iniciarTimerPreJuego() {
        lblTitulo.setText("NIVEL 1"); // Establecer el título del nivel
        tiempoRestante = 4; // Tiempo de pre-juego en segundos

        // Crear una línea de tiempo para el temporizador
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            tiempoRestante--; // Decrementar el tiempo restante

            if (tiempoRestante <= 0) {
                timeline.stop(); // Detener el temporizador
                iniciarJuego(); // Iniciar el juego
                iniciarTimer(); // Iniciar el temporizador del juego
                return;
            }

            lblTiempo.setText("Tiempo: " + tiempoRestante); // Actualizar la etiqueta de tiempo
        }));
        timeline.setCycleCount(tiempoRestante); // Establecer el número de ciclos del temporizador
        timeline.play(); // Iniciar la línea de tiempo

        // Deshabilitar la interacción con la interfaz durante el contador de prejuego
        gridCartas.setDisable(true);
        paneMensaje.setDisable(true);
    }

    /**
     * Método para cargar las imágenes de las cartas.
     */
    private void cargarImagenes() {
        imagenes = new ArrayList<>();
        imagenes.add("/Img/nivel1/Father-Padre.png");
        imagenes.add("/Img/nivel1/Cousin-Primo.png");
        imagenes.add("/Img/nivel1/Grandson-Nieto.png");
        imagenes.add("/Img/nivel1/Uncle-Tio.png");
        imagenes.add("/Img/nivel1/Aunt-Tia.png");

        // Duplicar las imágenes para tener las parejas
        imagenes.addAll(imagenes);
        Collections.shuffle(imagenes); // Mezclar las imágenes
    }

    /**
     * Método para iniciar el juego.
     */
    private void iniciarJuego() {
        cartasVolteadas = new ArrayList<>();
        botonesCartas = new ArrayList<>();
        parejasRestantes = imagenes.size() / 2; // Inicializar el número de parejas restantes

        // Habilitar la interacción con las cartas
        gridCartas.setDisable(false);

        // Crear y agregar las cartas al GridPane
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
                    voltearCarta(carta, imageViewPorDefecto, indice); // Voltear la carta al hacer clic
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            botonesCartas.add(carta); // Agregar la carta a la lista de botones
            gridCartas.add(carta, i % 5, i / 5); // Agregar la carta al GridPane
        }
    }

    /**
     * Método para voltear una carta.
     * @param carta El botón que representa la carta.
     * @param imageViewPorDefecto El ImageView con la imagen por defecto de la carta.
     * @param indice El índice de la carta en la lista de imágenes.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void voltearCarta(Button carta, ImageView imageViewPorDefecto, int indice) throws IOException {
        if (enProcesoDeComparacion) {
            return; // Si hay una comparación en proceso, no hacer nada
        }

        carta.setDisable(true); // Deshabilitar la carta temporalmente

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
        imageView.setFitWidth(80);
        imageView.setFitHeight(80);

        // Animación de rotación para voltear la carta
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

            cartasVolteadas.add(carta); // Agregar la carta a la lista de cartas volteadas

            if (cartasVolteadas.size() == 2) {
                enProcesoDeComparacion = true; // Marcar que se está realizando una comparación
                String rutaPrimeraCarta = (String) cartasVolteadas.get(0).getUserData();
                String rutaSegundaCarta = rutaImagen;

                if (rutaPrimeraCarta.equals(rutaSegundaCarta)) {
                    // Si las cartas coinciden
                    for (Button cartaVolteada : cartasVolteadas) {
                        cartaVolteada.setDisable(true); // Deshabilitar las cartas coincididas
                    }
                    cartasVolteadas.clear();
                    parejasRestantes--;
                    enProcesoDeComparacion = false;
                    if (parejasRestantes == 0) {
                        try {
                            timeline.stop(); // Detener el temporizador
                            mostrarMensaje("¡Felicidades!", "Has ganado el juego.", true); // Mostrar mensaje de victoria
                        } catch (IOException ex) {
                            Logger.getLogger(JuegoVistaLevel1Controller.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } else {
                    // Si las cartas no coinciden
                    Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                        for (Button cartaVolteada : cartasVolteadas) {
                            cartaVolteada.setDisable(false);
                            StackPane sp = (StackPane) cartaVolteada.getGraphic();
                            sp.getChildren().clear(); // Limpiar el StackPane
                            sp.getChildren().add(imageViewPorDefecto); // Restaurar la imagen por defecto
                        }
                        cartasVolteadas.clear();
                        enProcesoDeComparacion = false;
                    }));
                    timeline.play(); // Iniciar la línea de tiempo para restaurar las cartas
                }
            } else {
                carta.setUserData(rutaImagen); // Establecer los datos de la carta volteada
            }
        });
        volteo.play(); // Iniciar la animación de volteo
    }

    /**
     * Método para mostrar un mensaje al jugador.
     * @param titulo El título del mensaje.
     * @param mensaje El contenido del mensaje.
     * @param esVictoria Indicador de si el mensaje es de victoria.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    private void mostrarMensaje(String titulo, String mensaje, boolean esVictoria) throws IOException {
        lblMensaje.setText(mensaje); // Establecer el mensaje
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

        gridCartas.setDisable(true); // Deshabilitar la interacción con las cartas
        btnReiniciar.setDisable(false);
        btnIrLobby.setDisable(false);
    }

    /**
     * Método para iniciar el temporizador del juego.
     */
    private void iniciarTimer() {
        tiempoRestante = 31; // Iniciar desde 30 segundos
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            tiempoRestante--; // Decrementar el tiempo restante
            lblTiempo.setText("Tiempo: " + tiempoRestante); // Actualizar la etiqueta de tiempo
            if (tiempoRestante <= 0) {
                timeline.stop(); // Detener el temporizador
                try {
                    mostrarMensaje("¡Time Over!", "Se acabó el tiempo. ¡Inténtalo de nuevo!", false); // Mostrar mensaje de tiempo agotado
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE); // Ejecutar indefinidamente
        timeline.play(); // Iniciar la línea de tiempo
    }

    //endregion

}
