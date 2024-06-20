module com.example.duoreadmastermemorypoli {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;


    opens com.example.duoreadmastermemorypoli to javafx.fxml;
    exports com.example.duoreadmastermemorypoli;
}