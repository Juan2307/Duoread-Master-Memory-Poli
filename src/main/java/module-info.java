module com.example.duoreadmastermemorypoli {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires org.testfx.junit5;
    requires org.mockito;
    requires org.mockito.junit.jupiter;

    opens com.example.duoreadmastermemorypoli to javafx.fxml, org.testfx.junit5;
    exports com.example.duoreadmastermemorypoli;
}
