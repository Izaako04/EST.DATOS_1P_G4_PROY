/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.proyectoed1;

import ec.edu.espol.proyectoed1.classes.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author isaias
 */
public class vPaginaPrincipalController implements Initializable {

    @FXML
    private VBox contenedorHbox;
    @FXML
    private Button btnMisVehiculos;
    @FXML
    private Button btnVenderVehiculo;
    @FXML
    private Button vCerrarSesión;
    @FXML
    private Text textoSaludoUsuario;
    @FXML
    private ComboBox<String> cmbTipoVehiculo; // probando!!
    @FXML
    private ComboBox<?> cmbMarca;
    @FXML
    private ComboBox<?> cmbModelo;
    @FXML
    private TextField tfPrecioDesde;
    @FXML
    private TextField tfPrecioHasta;
    @FXML
    private TextField tfKmHasta;
    @FXML
    private Button btnBuscar;
    @FXML
    private TextField tfYearDesde;
    @FXML
    private TextField tfYearHasta;
    @FXML
    private ComboBox<?> cmbOrdenar;

    private Parent root;
    private Stage stage;
    private Scene scene;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // solo para probar estilos del combo box
        ObservableList<String> options = FXCollections.observableArrayList(
                "Opción 1",
                "Opción 2",
                "Opción 3"
        );

        // Agregar las opciones al ComboBox
        cmbTipoVehiculo.setItems(options);
        
        // El cuadro morado solo es para identificar el anchopane, cuando la parte del sistema
        // esté terminada y se puedan colocar los autos con sus fotos el fondo pasará a negro
    }    
    
    public void home (Usuario user) {
        textoSaludoUsuario.setText("Bienvenido " + user.getNombre());
        
        btnVenderVehiculo.setOnAction(event -> {
            try {
                mostrarSubirVehiculo(event, user); // deberia pasar user
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
    
    public void mostrarSubirVehiculo (Event event, Usuario user) throws IOException { // pasar argumentos !!        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vSubirVehiculo.fxml"));
        root = loader.load();

        vSubirVehiculoController vSubirVehiculoController = loader.getController();
        vSubirVehiculoController.home(user); // pasar argumentos (user, this.controller) idk

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1280, 720);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show(); 
    }
}