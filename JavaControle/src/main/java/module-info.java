module com.example.javacontrole {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.javacontrole to javafx.fxml;
    exports com.example.javacontrole;
}