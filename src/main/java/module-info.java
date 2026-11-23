module org.example.drawproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.swing;


    opens org.example.drawproject to javafx.fxml;
    exports org.example.drawproject;
    exports Model_Rebild to javafx.fxml;
    exports Model;
    opens Model to javafx.fxml;
}