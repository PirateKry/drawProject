module org.example.drawproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.drawproject to javafx.fxml;
    exports org.example.drawproject;
    exports Model;
    opens Model to javafx.fxml;
}