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

public class vCrearCuentaController {

    @FXML
    private ImageView imagenCrearC;
    @FXML
    private TextField fcNombreApellido;
    @FXML
    private TextField fcEmail;
    @FXML
    private TextField fcUsername;
    @FXML
    private PasswordField fcPassword;
    @FXML
    private CheckBox cbAceptoT;
    @FXML
    private Button bcrearCuenta;
    @FXML
    private Text tIniciarSesion;
    
    private Parent root;
    private Stage stage;
    private Scene scene;

    @FXML
    private void initialize() {
        home();
    }
    
    private void switchToPrimary() throws IOException {
        App.setRoot("vInicioSesion");
    }

    private void muestraAlerta (String titulo, String mssg) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mssg);
        alert.showAndWait();
    }
    
    @FXML
    private void crearCuenta(MouseEvent event) {
        String nombre = this.fcNombreApellido.getText();
        String cedula = this.fcUsername.getText();
        String contrasenia = this.fcPassword.getText();
        String correo = this.fcEmail.getText();
        boolean camposVacios = (nombre.isBlank() || cedula.isBlank() || contrasenia.isBlank() || correo.isBlank());
        
        if (camposVacios) {
            muestraAlerta("Erorr al crear el usuario", "Por favor llena todos los campos.");
        } else if (!cbAceptoT.isSelected()) {
            muestraAlerta("Erorr al crear el usuario", "Por favor acepta los términos y condiciones.");
        } else {
            Usuario u = new Usuario (correo, contrasenia, nombre, cedula);
            
            boolean uExiste = Sistema.verificarExistenciaUsuario_ARCHIVO (correo, cedula);
            
            if (uExiste) {
                muestraAlerta("Erorr al crear el usuario", "Ya existe un usuario registrado con ésa cédula o correo");
            
            } else {
                Sistema.agregarUsuario_Archivo(u);
                vaciarFields();
                muestraAlerta("Felicidades", "¡Ya eres parte de la familia AutoTrade!");
                ventanaIniciarSesion( event);
            }
        }
    }
    
    private void vaciarFields () {
        fcNombreApellido.setText("");
        fcUsername.setText("");
        fcPassword.setText("");
        fcEmail.setText("");
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

        fcUsername.setTextFormatter(textFormatter);

        tIniciarSesion.setOnMouseClicked(event -> {
            try {
                mostrarIniciarSesion(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
    }

    @FXML
    private void ventanaIniciarSesion(MouseEvent event) {
        try{
            App.setRoot("vInicioSesion");
        } catch(IOException e){
        }
    }
    
    private void mostrarIniciarSesion(MouseEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vInicioSesion.fxml"));
        root = loader.load();

        vInicioSesionController vInicioSesionController = loader.getController();
        vInicioSesionController.home();
        
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}