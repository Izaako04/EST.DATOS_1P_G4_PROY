package ec.edu.espol.proyectoed1;

import java.io.IOException;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ec.edu.espol.proyectoed1.classes.Persona;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class vInicioSesionController {

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
    
    private Parent root;
    private Stage stage;
    private Scene scene;

    // comentario
    private void switchToSecondary() throws IOException {
        App.setRoot("vCrearCuenta");
    }

    @FXML
    private void iniciarSesion(MouseEvent event) throws IOException {
        String correo = this.fUsuario.getText();
        String contra = this.fPassword.getText();
        //import
        
        
        
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vPaginaPrincipal.fxml"));
        root = loader.load();
            
        vPaginaPrincipalController vPaginaPrincipalController = loader.getController();
        vPaginaPrincipalController.home();
            
            
            
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root, 1280, 720); 
            stage.setScene(scene);
            
            // Centrar la ventana en la pantalla
            Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
            stage.setX((primaryScreenBounds.getWidth() - stage.getWidth()) / 2);
            stage.setY((primaryScreenBounds.getHeight() - stage.getHeight()) / 2);
            stage.show();
    }

    @FXML
    private void vCrearCuenta(MouseEvent event) throws IOException {
        App.setRoot("vCrearCuenta");
    }
    
    public void home(){
        fCrearCuenta.setOnMouseClicked(event -> {
            try {
                mostrarCrearCuenta(event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        
    }
    
    private void mostrarCrearCuenta(Event event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vCrearCuentaController.fxml"));
        root = loader.load();
            
        vCrearCuentaController vCrearCuentaController = loader.getController();
        vCrearCuentaController.home();
            
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();        
        
    }
}
