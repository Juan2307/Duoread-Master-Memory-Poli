
/**
 * Módulo principal de la aplicación DuoRead Master Memory Poli.
 *
 * Este módulo configura las dependencias y la visibilidad de los paquetes
 * para la aplicación. Se asegura de que las clases necesarias estén disponibles
 * y exporta los paquetes relevantes.
 */
module com.example.duoreadmastermemorypoli {

    // Requiere el módulo de controles de JavaFX para la interfaz gráfica de usuario
    requires javafx.controls;

    // Requiere el módulo de FXML de JavaFX para la carga de archivos FXML
    requires javafx.fxml;

    // Requiere el módulo de registro de Java para el registro de eventos y errores
    requires java.logging;

    // Requiere el módulo de TestFX para pruebas de la interfaz gráfica de usuario
    requires org.testfx.junit5;

    // Requiere el módulo de Mockito para la creación de mocks en pruebas unitarias
    requires org.mockito;

    // Requiere el módulo de integración de Mockito con JUnit Jupiter
    requires org.mockito.junit.jupiter;

    // Abre el paquete com.example.duoreadmastermemorypoli a JavaFX FXML y TestFX
    opens com.example.duoreadmastermemorypoli to javafx.fxml, org.testfx.junit5;

    // Exporta el paquete com.example.duoreadmastermemorypoli para que pueda ser usado por otros módulos
    exports com.example.duoreadmastermemorypoli;

}
