package ec.edu.espol.proyectoed1;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ec.edu.espol.proyectoed1.classes.Persona;
import ec.edu.espol.proyectoed1.classes.Usuario;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class vVisualizacionController {
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private Button bAccidentes;
    @FXML
    private TextField fcMarca;
    @FXML
    private TextField fcModelo;
    @FXML
    private TextField fcKm;
    @FXML
    private TextField fcAnio;
    @FXML
    private TextField fcMotor;
    @FXML
    private TextField fcTrans;
    @FXML
    private TextField fcUbicacion;
    @FXML
    private TextField fcPrecio;
    @FXML
    private Text tGuardar;
    @FXML
    private Button btOutMoverIzquierda;
    @FXML
    private Button btOutMoverDerecha;
    @FXML
    private Button btInMoverDerecha;
    @FXML
    private Button btInMoverIzquierda;
    @FXML
    private TextField fcPotencia;
    @FXML
    private TextField fcCombustible;
    @FXML
    private Button btRegresar;

    private void initialize() {
        home();
    }
    
    private void switchToPrimary() throws IOException {
        App.setRoot("vPaginaPrincipal");
    }

    private void muestraAlerta (String titulo, String mssg) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mssg);
        alert.showAndWait();
    }
    
    
    public void home(){
        // Configurar el TextFormatter para aceptar solo valores numéricos
        TextFormatter<Integer> textFormatter = new TextFormatter<Integer> (
            new IntegerStringConverter(),
            null, // Valor inicial
            change -> {
                if (change.isAdded() && !change.getText().matches("\\d*\\.?\\d*")) {
                    return null;
                }
                return change;
            }
        );

        fcPrecio.setTextFormatter(textFormatter);

        tGuardar.setOnMouseClicked(event -> {
            try {
                verAccidentes(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
    }

    private void ventanaIniciarSesion(MouseEvent event) {
        try{
            App.setRoot("vInicioSesion");
        } catch(IOException e){
        }
    }    
    @FXML
    private void verAccidentes(MouseEvent event) throws IOException {
    }
}