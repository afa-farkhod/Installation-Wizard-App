module com.example.installerwizard {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.datatransfer;
    requires java.desktop;


    opens com.example.installerwizard to javafx.fxml;
    exports com.example.installerwizard;
}