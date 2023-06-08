module com.example.instalwizard {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens com.example.instalwizard to javafx.fxml;
    exports com.example.instalwizard;
}