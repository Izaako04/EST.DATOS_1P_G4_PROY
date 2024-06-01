/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectoed1;

import ec.edu.espol.proyectoed1.classes.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Isaías
 */
public class vSubirVehiculoController implements Initializable {

    @FXML
    private TextField fcPrecio;
    @FXML
    private TextField fcKilometraje;
    @FXML
    private ComboBox<?> cmbMarca;
    @FXML
    private ComboBox<?> cmbModelo;
    @FXML
    private TextField fcYear;
    @FXML
    private TextField fcPlaca;
    @FXML
    private TextField fcMotor;
    @FXML
    private CheckBox cbTieneAccidente;
    @FXML
    private DatePicker dpFechaAccidente;
    @FXML
    private TextField fcUbicacionAccidente;
    @FXML
    private TextArea taDescripcionAccidente;
    @FXML
    private CheckBox cbTieneReparacion;
    @FXML
    private DatePicker dpFechaReparacion;
    @FXML
    private TextArea taDescripcionReparacion;
    @FXML
    private TextField fcUbicacion;
    @FXML
    private ComboBox<?> cmbTiransmision;
    @FXML
    private Button btnExteriorFrontal;
    @FXML
    private Button btnExteriorLateral;
    @FXML
    private Button btnExteriorTrasero;
    @FXML
    private Button btnInterior;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnRegresar;
    
    private Parent root;
    private Stage stage;
    private Scene scene;
    @FXML
    private ImageView portadaVehiculo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    public void home (Usuario user) {
        btnRegresar.setOnAction(event -> {
            try {
                regresar(user, event);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
    
    public void regresar(Usuario user, Event event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vPaginaPrincipal.fxml"));
        root = loader.load();
            
        vPaginaPrincipalController vPaginaPrincipalController = loader.getController();
        // vPaginaPrincipalController.actualizarVehiculo(); alguna función para 'recargar' los vehiculos
        vPaginaPrincipalController.home(user);
            
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();    
    
    }
    
    
}
