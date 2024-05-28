module ec.edu.espol.proyectoed1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens ec.edu.espol.proyectoed1 to javafx.fxml;
    exports ec.edu.espol.proyectoed1;
}
