package ec.edu.espol.proyectoed1;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class PrimaryController {

    @FXML
    private Button bIniciarSesion;
    @FXML
    private TextField fUsuario;
    @FXML
    private PasswordField fPassword;
    @FXML
    private Text tBienvenido;
    @FXML
    private Text tTexto;
    @FXML
    private Text fCrearCuenta;
    @FXML
    private Text titPixelPlay;
    @FXML
    private ImageView miLogo;

    // comentario
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void iniciarSesion(MouseEvent event) {
    }

    @FXML
    private void vCrearCuenta(MouseEvent event) {
    }
}
