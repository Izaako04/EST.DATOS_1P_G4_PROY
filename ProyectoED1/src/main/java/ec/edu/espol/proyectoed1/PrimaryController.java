package ec.edu.espol.proyectoed1;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    // comentario
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
