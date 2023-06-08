module com.example.installerwizard {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.installerwizard to javafx.fxml;
    exports com.example.installerwizard;
}